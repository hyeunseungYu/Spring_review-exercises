package hanghae11.springexample.likes.service;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Likes;
import hanghae11.springexample.entity.Member;
import hanghae11.springexample.entity.Reply;
import hanghae11.springexample.jwt.JwtUtil;
import hanghae11.springexample.likes.LikesRepository;
import hanghae11.springexample.likes.dto.LikesRequestDto;
import hanghae11.springexample.likes.dto.LikesRequestMsgDto;
import hanghae11.springexample.member.repository.MemberRepository;
import hanghae11.springexample.reply.dto.ReplyNullReplaceDto;
import hanghae11.springexample.reply.dto.ReplyRequestMsgDto;
import hanghae11.springexample.reply.repository.ReplyRepository;
import hanghae11.springexample.repository.FreeBoardRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final JwtUtil jwtUtil;
    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final ReplyRepository replyRepository;

    public LikesRequestMsgDto postLikes(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }

            //댓글 남기는 사용자 확인
            Member member = memberRepository.findByUsername(claims.getSubject());

            //param에 해당하는 게시글 찾기
            FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
            );


            Likes likes = Likes.builder()
                    .likeCheck(1)
                    .freeboard(freeBoard)
                    .member(member)
                    .username(member.getUsername())
                    .build();

            likesRepository.save(likes);

            Integer count = likesRepository.countByFreeBoardLikes(freeBoard);

            return new LikesRequestMsgDto("좋아요 등록", HttpStatus.OK.value(), count);
        } else {
            return new LikesRequestMsgDto("토큰이 유효하지 않습니다", HttpStatus.BAD_REQUEST.value(),0);
        }
    }

    public LikesRequestMsgDto postReplyLikes(Long id, Long replyId, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }

            //댓글 남기는 사용자 확인
            Member member = memberRepository.findByUsername(claims.getSubject());

            //param에 해당하는 게시글 찾기
            FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
            );

            //param에 해당하는 게시글에 달린 댓글 찾기
            Reply reply = replyRepository.findById(replyId).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
            );

            Likes likes = Likes.builder()
                    .likeCheck(1)
                    .freeboard(freeBoard)
                    .member(member)
                    .reply(reply)
                    .username(member.getUsername())
                    .build();

            likesRepository.save(likes);

            Integer count = likesRepository.countByReplyLikes(reply);
            return new LikesRequestMsgDto("좋아요 등록", HttpStatus.OK.value(),count);
        } else {
            return new LikesRequestMsgDto("토큰이 유효하지 않습니다", HttpStatus.BAD_REQUEST.value(),0);
        }
    }

    public LikesRequestMsgDto deletePostLikes(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }

            //댓글 남기는 사용자 확인
            Member member = memberRepository.findByUsername(claims.getSubject());

            //param에 해당하는 게시글 찾기
            FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
            );

            Likes likes = likesRepository.findByUsername(member.getUsername());

            if (!likes.getUsername().equals(member.getUsername())) {
                return new LikesRequestMsgDto("잘못된 시도입니다.", HttpStatus.BAD_REQUEST.value(),0);
            }
            likesRepository.delete(likes);

            Integer count = likesRepository.countByFreeBoardLikes(freeBoard);

            return new LikesRequestMsgDto("좋아요 취소", HttpStatus.OK.value(),count);
        } else {
            return new LikesRequestMsgDto("토큰이 유효하지 않습니다", HttpStatus.BAD_REQUEST.value(),0);
        }
    }

    public LikesRequestMsgDto deleteReplyLikes(Long id, Long replyId, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }

            //댓글 남기는 사용자 확인
            Member member = memberRepository.findByUsername(claims.getSubject());

            //param에 해당하는 게시글 찾기
            FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
            );

            //param에 해당하는 게시글에 달린 댓글 찾기
            Reply reply = replyRepository.findById(replyId).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
            );

            Likes likes = likesRepository.findByUsername(member.getUsername());

            if (!likes.getUsername().equals(member.getUsername())) {
                return new LikesRequestMsgDto("잘못된 시도입니다.", HttpStatus.BAD_REQUEST.value(),0);
            }

            likesRepository.delete(likes);

            Integer count = likesRepository.countByReplyLikes(reply);
            return new LikesRequestMsgDto("좋아요 취소", HttpStatus.OK.value(),count);
        } else {
            return new LikesRequestMsgDto("토큰이 유효하지 않습니다", HttpStatus.BAD_REQUEST.value(),0);
        }
    }
}

package hanghae11.springexample.likes.service;


import hanghae11.springexample.freeBoard.entity.*;
import hanghae11.springexample.jwt.JwtUtil;
import hanghae11.springexample.likes.PostLikesRepository;
import hanghae11.springexample.likes.ReplyLikesRepository;
import hanghae11.springexample.likes.dto.LikesRequestMsgDto;
import hanghae11.springexample.likes.entity.PostLikes;
import hanghae11.springexample.likes.entity.ReplyLikes;
import hanghae11.springexample.member.entity.Member;
import hanghae11.springexample.member.repository.MemberRepository;
import hanghae11.springexample.reply.entity.Reply;
import hanghae11.springexample.reply.repository.ReplyRepository;
import hanghae11.springexample.freeBoard.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final JwtUtil jwtUtil;
    private final PostLikesRepository postLikesRepository;
    private final ReplyLikesRepository replyLikesRepository;
    private final MemberRepository memberRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final ReplyRepository replyRepository;

    public LikesRequestMsgDto postLikes(Long id, Member member) {

        //param에 해당하는 게시글 찾기
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        //사용자가 이전에 좋아요를 눌렀는지 확인
        //사용자가 이전에 좋아요를 눌렀다면 사용자의 이름과 게시글의 id가 이미 있을 것임
        if (postLikesRepository.existsByUsername(member.getUsername()) &&
            postLikesRepository.findByUsername(member.getUsername()).getFreeBoardPostLikes().getId().equals(id)) {

            Integer count = postLikesRepository.countByFreeBoardPostLikes(freeBoard);
            return new LikesRequestMsgDto("이미 좋아요를 누른 게시글입니다.", HttpStatus.ACCEPTED.value(), count);
        }

        //좋아요를 눌렀다는 상태를 표현하기 위해 likecheck 넣었음. 지금 당장은 의미 없음
        PostLikes postLikes = PostLikes.builder()
                .likeCheck(1)
                .freeboard(freeBoard)
                .member(member)
                .username(member.getUsername())
                .build();

        postLikesRepository.save(postLikes);

        Integer count = postLikesRepository.countByFreeBoardPostLikes(freeBoard);

        return new LikesRequestMsgDto("좋아요 등록", HttpStatus.OK.value(), count);
    }


    public LikesRequestMsgDto postReplyLikes(Long id, Long replyId, Member member) {

        //param에 해당하는 게시글 찾기
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        //param에 해당하는 게시글에 달린 댓글 찾기
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        //사용자가 이전에 댓글에 좋아요를 눌렀는지 확인. 하기 세가지 조건을 만족하면 이미 좋아요를 누른 상태
        if (replyLikesRepository.existsByUsername(member.getUsername()) &&
            replyLikesRepository.findByUsername(member.getUsername()).getFreeBoardReplyLikes().getId().equals(id) &&
            replyLikesRepository.findByUsername(member.getUsername()).getReplyLikes().getId().equals(replyId)) {

            Integer count = replyLikesRepository.countByReplyLikes(reply);
            return new LikesRequestMsgDto("이미 좋아요를 누른 댓글입니다.", HttpStatus.ACCEPTED.value(), count);
        }

        ReplyLikes replyLikes = ReplyLikes.builder()
                .likeCheck(1)
                .freeBoardReplyLikes(freeBoard)
                .memberReplyLikes(member)
                .replyLikes(reply)
                .username(member.getUsername())
                .build();


        replyLikesRepository.save(replyLikes);

        Integer count = replyLikesRepository.countByReplyLikes(reply);
        return new LikesRequestMsgDto("좋아요 등록", HttpStatus.OK.value(),count);
    }

    public LikesRequestMsgDto deletePostLikes(Long id, Member member) {

        //param에 해당하는 게시글 찾기
        FreeBoard freeBoard = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        //좋아요를 누르지 않았는데 이 메소드가 호출되는 경우
        if (!postLikesRepository.existsByUsername(member.getUsername())) {
            return new LikesRequestMsgDto("잘못된 시도입니다.", HttpStatus.BAD_REQUEST.value(),0);
        }

        PostLikes postLikes = postLikesRepository.findByUsername(member.getUsername());

        if (!postLikes.getUsername().equals(member.getUsername())) {
            return new LikesRequestMsgDto("잘못된 시도입니다.", HttpStatus.BAD_REQUEST.value(),0);
        }
        postLikesRepository.delete(postLikes);

        Integer count = postLikesRepository.countByFreeBoardPostLikes(freeBoard);

        return new LikesRequestMsgDto("좋아요 취소", HttpStatus.OK.value(),count);
    }

    public LikesRequestMsgDto deleteReplyLikes(Long id, Long replyId, Member member) {

        //param에 해당하는 게시글 찾기
        FreeBoard
                freeBoard = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        //param에 해당하는 게시글에 달린 댓글 찾기
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        //좋아요를 누르지 않았는데 이 메소드가 호출되는 경우
        if (!replyLikesRepository.existsByUsername(member.getUsername())) {
            return new LikesRequestMsgDto("잘못된 시도입니다.", HttpStatus.BAD_REQUEST.value(),0);
        }

        ReplyLikes replyLikes = replyLikesRepository.findByUsername(member.getUsername());


        if (!replyLikes.getUsername().equals(member.getUsername())) {
            return new LikesRequestMsgDto("잘못된 시도입니다.", HttpStatus.BAD_REQUEST.value(),0);
        }

        replyLikesRepository.delete(replyLikes);

        Integer count = replyLikesRepository.countByReplyLikes(reply);
        return new LikesRequestMsgDto("좋아요 취소", HttpStatus.OK.value(),count);
    }
}

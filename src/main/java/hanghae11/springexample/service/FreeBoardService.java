package hanghae11.springexample.service;

import hanghae11.springexample.dto.*;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Member;
import hanghae11.springexample.entity.MemberRoleEnum;
import hanghae11.springexample.jwt.JwtUtil;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;

import hanghae11.springexample.member.dto.memberDto;
import hanghae11.springexample.member.dto.memberRequestDto;
import hanghae11.springexample.repository.FreeBoardRepository;
import hanghae11.springexample.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;


    public List<FreeBoardRequestDto> getFreeBoardInfo() {
        List<FreeBoard> freeBoardList = freeBoardRepository.findAllByOrderByModifiedAtDesc();

        List<FreeBoardRequestDto> freeBoardDtoList = new ArrayList<>();
        for (FreeBoard freeBoard : freeBoardList) {
            FreeBoardRequestDto freeBoardRequestDto = new FreeBoardRequestDto(freeBoard);
            freeBoardDtoList.add(freeBoardRequestDto);
        }
        return freeBoardDtoList;
    }


    @Transactional
    public memberRequestDto createPost(CreatePostRequestDto createPostRequestDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token error");
            }

            Member member = memberRepository.findByUsername(claims.getSubject());

            FreeBoard freeBoard = FreeBoard.builder()
                    .username(claims.getSubject())
                    .titles(createPostRequestDto.getTitles())
                    .contents(createPostRequestDto.getContents())
                    .member(member).build();


            freeBoardRepository.save(freeBoard);
            return new memberRequestDto(freeBoard, new memberDto(member));

        }else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }

    }

    public FreeBoardRequestDto targetSearch(Long id) {
        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        return new FreeBoardRequestDto(entity);
    }


@Transactional
    public SignupRequestMsgDto freeBoardEditRequestDto(Long id, FreeBoardEditRequestDto freeBoardEditRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                return new SignupRequestMsgDto("토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST.value());
            }
            //토큰에 들어있는 username으로 member 찾기
            Member member = memberRepository.findByUsername(claims.getSubject());


            //param에 해당하는 게시글 찾기
            FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
            );

            //사용자가 admin이면 바로 수정
            if (member.getRole().equals(MemberRoleEnum.ADMIN)) {
                entity.update(freeBoardEditRequestDto, member);
                return new SignupRequestMsgDto("수정하였습니다.", HttpStatus.OK.value());
            }

            //토큰에 들어있는 이름과 게시글의 이름이 다르면 토큰을 가진 사용자가 작성한 글이 아님
            if (!entity.getUsername().equals(member.getUsername())){
                return new SignupRequestMsgDto("본인이 작성한 글만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value());
            }

            entity.update(freeBoardEditRequestDto, member);
            return new SignupRequestMsgDto("수정하였습니다.", HttpStatus.OK.value());

        } else {
            return new SignupRequestMsgDto("토큰이 유효하지 않습니다",HttpStatus.BAD_REQUEST.value());
        }
    }

    public SignupRequestMsgDto freeBoardDelete(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                return new SignupRequestMsgDto("토큰이 유효하지 않습니다.",HttpStatus.BAD_REQUEST.value());
            }

            Member member = memberRepository.findByUsername(claims.getSubject());

            FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("작성한 게시글이 존재하지 않습니다.")
            );

            //사용자가 admin이면 바로 삭제
            if (member.getRole().equals(MemberRoleEnum.ADMIN)) {
                freeBoardRepository.deleteById(id);
                return new SignupRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());
            }

            if (!entity.getUsername().equals(member.getUsername())){
                return new SignupRequestMsgDto("본인이 작성한 글만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value());
            }


            freeBoardRepository.deleteById(id);
            return new SignupRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());

        }else {

            return new SignupRequestMsgDto("토큰이 유효하지 않습니다.",HttpStatus.BAD_REQUEST.value());
        }

    }


}

package hanghae11.springexample.service;

import hanghae11.springexample.dto.*;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Member;
import hanghae11.springexample.jwt.JwtUtil;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;
import hanghae11.springexample.member.repository.MemberRpository;
import hanghae11.springexample.repository.FreeBoardRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;
    private final JwtUtil jwtUtil;
    private final MemberRpository memberRpository;


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
    public memberRequestDto createPost(FreeBoardDto freeBoardDto, HttpServletRequest request) {

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

            Member member = memberRpository.findByUsername(claims.getSubject());

            FreeBoard freeBoard = new FreeBoard(freeBoardDto, member);
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
                throw new IllegalArgumentException("Token Error");
            }

            Member member = memberRpository.findByUsername(claims.getSubject());

            FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("작성한 게시글이 존재하지 않습니다.")
            );

            if (!entity.getUsername().equals(member.getUsername())){
                return new SignupRequestMsgDto("일치하는 아이디를 찾을 수 없습니다.", "400");
            }

            entity.update(freeBoardEditRequestDto, member);
            return new SignupRequestMsgDto("수정하였습니다.", "200");

        } else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
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
                throw new IllegalArgumentException("Token Error");
            }

            Member member = memberRpository.findByUsername(claims.getSubject());

            FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("작성한 게시글이 존재하지 않습니다.")
            );

            if (!entity.getUsername().equals(member.getUsername())){
                return new SignupRequestMsgDto("일치하는 아이디를 찾을 수 없습니다.", "400");
            }


            freeBoardRepository.deleteById(id);
            return new SignupRequestMsgDto("삭제하였습니다.", "200");

        }else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }

    }

//    public FreeBoardPasswordRequestMsgDto freeBoardPasswordRequestDto(Long id, FreeBoardPasswordRequestDto freeBoardPasswordRequestDto,
//                                                                      FreeBoardPasswordRequestMsgDto freeBoardPasswordRequestMsgDto) {
//        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
//        );
//
//        if (entity.getPasswords().equals(freeBoardPasswordRequestDto.getPasswords())) {
//            freeBoardRepository.deleteById(id);
//            return freeBoardPasswordRequestMsgDto;
//        } else {
//            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
//        }
//
//    }
}

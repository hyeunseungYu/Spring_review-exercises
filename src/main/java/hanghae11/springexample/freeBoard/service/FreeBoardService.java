package hanghae11.springexample.freeBoard.service;


import hanghae11.springexample.freeBoard.dto.FreeBoardEditRequestDto;
import hanghae11.springexample.freeBoard.dto.FreeBoardRequestDto;
import hanghae11.springexample.freeBoard.entity.FreeBoard;
import hanghae11.springexample.member.entity.Member;
import hanghae11.springexample.member.entity.MemberRoleEnum;
import hanghae11.springexample.freeBoard.dto.CreatePostRequestDto;
import hanghae11.springexample.freeBoard.repository.FreeBoardRepository;
import hanghae11.springexample.jwt.JwtUtil;
import hanghae11.springexample.member.dto.SignupRequestMsgDto;

import hanghae11.springexample.member.dto.memberDto;
import hanghae11.springexample.member.dto.memberRequestDto;

import hanghae11.springexample.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public memberRequestDto createPost(CreatePostRequestDto createPostRequestDto, Member member) {

        FreeBoard freeBoard = FreeBoard.builder()
                .username(member.getUsername())
                .titles(createPostRequestDto.getTitles())
                .contents(createPostRequestDto.getContents())
                .member(member).build();

        freeBoardRepository.save(freeBoard);
        return new memberRequestDto(freeBoard, new memberDto(member));
    }

    public FreeBoardRequestDto targetSearch(Long id) {
        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        return new FreeBoardRequestDto(entity);
    }

    @Transactional
    public SignupRequestMsgDto freeBoardEditRequestDto(Long id, FreeBoardEditRequestDto freeBoardEditRequestDto, Member member) {

        //param에 해당하는 게시글 찾기
        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        //사용자가 admin이면 바로 수정
        if (member.getRole().equals(MemberRoleEnum.ADMIN)) {
            entity.update(freeBoardEditRequestDto, member);
            return new SignupRequestMsgDto("수정하였습니다.", HttpStatus.OK.value());
        }

        //로그인한 사용자의 이름과 게시글의 이름이 다르면 해당 사용자가 작성한 글이 아님
        if (!entity.getUsername().equals(member.getUsername())) {
            return new SignupRequestMsgDto("본인이 작성한 글만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value());
        }

        entity.update(freeBoardEditRequestDto, member);
        return new SignupRequestMsgDto("수정하였습니다.", HttpStatus.OK.value());
    }

    public SignupRequestMsgDto freeBoardDelete(Long id, Member member) {

        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("작성한 게시글이 존재하지 않습니다.")
        );

        //사용자가 admin이면 바로 삭제
        if (member.getRole().equals(MemberRoleEnum.ADMIN)) {
            freeBoardRepository.deleteById(id);
            return new SignupRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());
        }

        if (!entity.getUsername().equals(member.getUsername())) {
            return new SignupRequestMsgDto("본인이 작성한 글만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value());
        }

        freeBoardRepository.deleteById(id);
        return new SignupRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());

    }
}

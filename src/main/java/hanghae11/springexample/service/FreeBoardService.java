package hanghae11.springexample.service;

import hanghae11.springexample.dto.*;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;


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
    public FreeBoard createPost(FreeBoardDto freeBoardDto) {
        FreeBoard freeBoard = new FreeBoard(freeBoardDto);
        freeBoardRepository.save(freeBoard);
        return freeBoard;
    }

    public FreeBoardRequestDto targetSearch(Long id) {
        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        return new FreeBoardRequestDto(entity);
    }


    @Transactional
    public FreeBoardEditRequestDto freeBoardEditRequestDto(Long id, FreeBoardEditRequestDto freeBoardEditRequestDto) {
        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );


        if (entity.getPasswords().equals(freeBoardEditRequestDto.getPasswords())) {
            entity.update(freeBoardEditRequestDto);
            return freeBoardEditRequestDto;
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }


    }

    public FreeBoardPasswordRequestMsgDto freeBoardPasswordRequestDto(Long id, FreeBoardPasswordRequestDto freeBoardPasswordRequestDto,
                                                                      FreeBoardPasswordRequestMsgDto freeBoardPasswordRequestMsgDto) {
        FreeBoard entity = freeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        if (entity.getPasswords().equals(freeBoardPasswordRequestDto.getPasswords())) {
            freeBoardRepository.deleteById(id);
            return freeBoardPasswordRequestMsgDto;
        } else {

            throw new RuntimeException("비밀번호가 일치하지 않습니다.");

        }

    }
}

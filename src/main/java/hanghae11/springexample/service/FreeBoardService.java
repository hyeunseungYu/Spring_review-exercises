package hanghae11.springexample.service;

import hanghae11.springexample.dto.FreeBoardDto;
import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;
    public List<FreeBoard> getPosts() {
        FreeBoard freeBoard = freeBoardRepository.findById()
        List<FreeBoard> getInfo = FreeBoardDto()
//        return freeBoardRepository.findAllByOrderByModifiedAtDesc();
    }


   @Transactional
    public FreeBoard createPost(FreeBoardDto freeBoardDto) {
        FreeBoard freeBoard = new FreeBoard(freeBoardDto);
        freeBoardRepository.save(freeBoard);
        return freeBoard;
    }
}

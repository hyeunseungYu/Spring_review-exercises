package hanghae11.springexample.freeBoard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FreeBoardEditRequestDto {

    private String titles;
    private String contents;


}

package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class FreeBoardDto {
    private String names;
    private String titles;
    private String contents;
    private Integer passwords;

}


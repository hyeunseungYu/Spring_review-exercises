package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FreeBoardEditRequestDto {

    private String titles;
    private String contents;


}

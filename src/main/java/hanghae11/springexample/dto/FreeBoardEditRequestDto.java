package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FreeBoardEditRequestDto {

    private String titles;
    private String contents;


//    public FreeBoardEditRequestDto(FreeBoard entity) {
//        this.titles = entity.getTitles();
//        this.contents = entity.getContents();
//
//    }


}

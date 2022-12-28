package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FreeBoardEditRequestDto {

    private String names;
    private String titles;
    private String contents;
    private Integer passwords;

    public FreeBoardEditRequestDto(FreeBoard entity) {
        this.names = entity.getNames();
        this.titles = entity.getTitles();
        this.contents = entity.getContents();
        this.passwords = entity.getPasswords();
    }


}

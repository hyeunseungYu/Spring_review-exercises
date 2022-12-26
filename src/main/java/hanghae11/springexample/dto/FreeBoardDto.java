package hanghae11.springexample.dto;

import lombok.Getter;

@Getter
public class FreeBoardDto {
    private String names;
    private String titles;
    private String contents;
    private Integer passwords;

    public FreeBoardDto(String names, String titles, String contents) {
        this.names = names;
        this.titles = titles;
        this.contents = contents;
    }
}


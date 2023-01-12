package hanghae11.springexample.freeBoard.dto;

import hanghae11.springexample.freeBoard.entity.FreeBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FreeBoardRequestDto {
    private String username;
    private String titles;
    private String contents;
    private LocalDateTime modifiedAt;

    public FreeBoardRequestDto(FreeBoard entity) {
        this.username = entity.getUsername();
        this.titles = entity.getTitles();
        this.contents = entity.getContents();
        this.modifiedAt = entity.getModifiedAt();

    }
}

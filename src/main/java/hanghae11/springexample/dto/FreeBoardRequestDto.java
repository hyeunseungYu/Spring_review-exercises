package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

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

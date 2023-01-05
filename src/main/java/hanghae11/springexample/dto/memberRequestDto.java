package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
public class memberRequestDto {
    private String username;
    private String titles;
    private String contents;
    private memberDto member;

    public memberRequestDto(FreeBoard freeBoard, memberDto memberdto) {
        this.username = freeBoard.getUsername();
        this.titles = freeBoard.getTitles();
        this.contents = freeBoard.getContents();
        this.member = memberdto;
    }


}

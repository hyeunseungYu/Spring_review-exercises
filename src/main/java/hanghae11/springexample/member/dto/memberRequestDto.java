package hanghae11.springexample.member.dto;

import hanghae11.springexample.freeBoard.entity.FreeBoard;
import lombok.Data;

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

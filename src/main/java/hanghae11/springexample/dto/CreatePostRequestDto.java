package hanghae11.springexample.dto;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreatePostRequestDto {
    private String username;
    private String titles;
    private String contents;
    private Member member;

    public CreatePostRequestDto(FreeBoard freeBoard) {
        this.contents = freeBoard.getContents();
        this.username = freeBoard.getUsername();
        this.titles = freeBoard.getTitles();
        this.member = freeBoard.getMember();
    }




}


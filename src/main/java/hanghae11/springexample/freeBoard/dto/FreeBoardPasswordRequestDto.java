package hanghae11.springexample.freeBoard.dto;

import hanghae11.springexample.freeBoard.entity.FreeBoard;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FreeBoardPasswordRequestDto {

    private Integer passwords;

    public FreeBoardPasswordRequestDto(FreeBoard entity) {
//        this.passwords = entity.getPasswords();
    }
}

package hanghae11.springexample.freeBoard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FreeBoardResponseDto {
    private List<FreeBoardRequestDto> freeBoardRequestDtoList;
    private Integer counter;
}

package hanghae11.springexample.entity;

import hanghae11.springexample.dto.FreeBoardDto;
import hanghae11.springexample.dto.FreeBoardEditRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class FreeBoard extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String names;

    @Column(nullable = false)
//    @JsonIgnore
    private Integer passwords;

    @Column(nullable = false)
    private String titles;

    @Column(nullable = false)
    private String contents;

    public FreeBoard (FreeBoardDto freeBoardDto) {
        this.contents = freeBoardDto.getContents();
        this.names = freeBoardDto.getNames();
        this.passwords = freeBoardDto.getPasswords();
        this.titles = freeBoardDto.getTitles();
    }

    public void update(FreeBoardEditRequestDto freeBoardEditRequestDto) {
        this.names = freeBoardEditRequestDto.getNames();
        this.contents = freeBoardEditRequestDto.getContents();
        this.titles = freeBoardEditRequestDto.getTitles();
    }

}

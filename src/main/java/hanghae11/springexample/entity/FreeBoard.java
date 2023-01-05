package hanghae11.springexample.entity;

import hanghae11.springexample.dto.FreeBoardDto;
import hanghae11.springexample.dto.FreeBoardEditRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class FreeBoard extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String titles;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



    public FreeBoard(FreeBoardDto freeBoardDto, Member member) {
        this.contents = freeBoardDto.getContents();
        this.username = freeBoardDto.getUsername();
        this.titles = freeBoardDto.getTitles();
        this.member = member;
    }

    public void update(FreeBoardEditRequestDto freeBoardEditRequestDto, Member member) {
        this.contents = freeBoardEditRequestDto.getContents();
        this.titles = freeBoardEditRequestDto.getTitles();
        this.member = member;
    }

}

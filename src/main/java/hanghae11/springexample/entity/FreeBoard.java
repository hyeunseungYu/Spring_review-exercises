package hanghae11.springexample.entity;

import hanghae11.springexample.dto.CreatePostRequestDto;
import hanghae11.springexample.dto.FreeBoardEditRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reply> replies = new ArrayList<>();

    @OneToMany(mappedBy = "freeBoardLikes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Likes> likes = new ArrayList<>();

//    public FreeBoard(CreatePostRequestDto createPostRequestDto) {
//        this.contents = createPostRequestDto.getContents();
//        this.username = createPostRequestDto.getUsername();
//        this.titles = createPostRequestDto.getTitles();
//        this.member = createPostRequestDto.getMember();
//    }

    private FreeBoard(FreeboardBuilder freeboardBuilder) {

        this.username = freeboardBuilder.username;
        this.titles = freeboardBuilder.titles;
        this.contents = freeboardBuilder.contents;
        this.member = freeboardBuilder.member;
    }

    //static이니까 다른곳에서 FreeBoard를 통해 바로 접근이 가능함
    //builder는 FreeboardBuilder 객체 생성해서 반환함
    public static FreeboardBuilder builder() {
        return new FreeboardBuilder();
    }

    public static class FreeboardBuilder {
        private String username;
        private String titles;
        private String contents;
        private Member member;

        //new 못쓰게
        protected FreeboardBuilder() {

        }

        public FreeboardBuilder username(String username) {
            this.username = username;
            return this;
        }

        public FreeboardBuilder titles(String titles) {
            this.titles = titles;
            return this;
        }

        public FreeboardBuilder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public FreeboardBuilder member(Member member) {
            this.member = member;
            return this;
        }

        //Freeboard 객체를 생성하면서 builder에 설정한 값을 넣어주면서 반환
        //빼먹으면 Freeboard 엔티티 객체 생성이 안됨
        public FreeBoard build() {
            return new FreeBoard(this);
        }


    }


    public void update(FreeBoardEditRequestDto freeBoardEditRequestDto, Member member) {
        this.contents = freeBoardEditRequestDto.getContents();
        this.titles = freeBoardEditRequestDto.getTitles();
        this.member = member;
    }

}

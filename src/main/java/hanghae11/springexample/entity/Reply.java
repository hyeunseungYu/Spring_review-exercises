package hanghae11.springexample.entity;

import hanghae11.springexample.reply.dto.ReplyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "freeBoard_id")
    private FreeBoard freeBoard;

    @OneToMany(mappedBy = "replyLikes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReplyLikes> replyLikes = new ArrayList<>();

    private Reply(ReplyBuilder replyBuilder) {
        this.contents = replyBuilder.contents;
        this.freeBoard = replyBuilder.freeBoard;
        this.username = replyBuilder.username;
    }

    public static ReplyBuilder builder() {
        return new ReplyBuilder();
    }

    public static class ReplyBuilder {
        private String contents;
        private FreeBoard freeBoard;
        private String username;


        protected ReplyBuilder() {

        }

        public ReplyBuilder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public ReplyBuilder freeboard(FreeBoard freeBoard) {
            this.freeBoard = freeBoard;
            return this;
        }

        public ReplyBuilder username(String username) {
            this.username = username;
            return this;
        }

        public Reply build() {
            return new Reply(this);
        }
    }

    public void update(ReplyRequestDto replyRequestDto, FreeBoard freeBoard) {
        this.contents = replyRequestDto.getContents();
        this.freeBoard = freeBoard;
    }

}

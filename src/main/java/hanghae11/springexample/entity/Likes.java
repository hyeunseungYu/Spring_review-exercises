package hanghae11.springexample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer likeCheck = 0;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "freeboard_id")
    private FreeBoard freeBoardLikes;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberLikes;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply replyLikes;

    private Likes(LikesBuilder likesBuilder) {
        this.likeCheck = likesBuilder.likeCheck;
        this.freeBoardLikes =likesBuilder.freeBoard;
        this.memberLikes = likesBuilder.member;
        this.replyLikes = likesBuilder.reply;
        this.username = likesBuilder.username;
    }

    public static LikesBuilder builder() {
        return new LikesBuilder();
    }

    public static class LikesBuilder {
        private Integer likeCheck;
        private FreeBoard freeBoard;
        private Member member;
        private Reply reply;
        private String username;

        protected LikesBuilder() {

        }

        public LikesBuilder likeCheck(Integer likeCheck) {
            this.likeCheck = likeCheck;
            return this;
        }

        public LikesBuilder freeboard(FreeBoard freeBoard) {
            this.freeBoard = freeBoard;
            return this;
        }

        public LikesBuilder member(Member member) {
            this.member = member;
            return this;
        }

        public LikesBuilder reply(Reply reply) {
            this.reply = reply;
            return this;
        }

        public LikesBuilder username(String username) {
            this.username = username;
            return this;
        }
        public Likes build() {
            return new Likes(this);
        }
    }

}

package hanghae11.springexample.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer likeCheck = 0;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "freeboard_id")
    private FreeBoard freeBoardReplyLikes;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberReplyLikes;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply replyLikes;

}

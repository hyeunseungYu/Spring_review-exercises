package hanghae11.springexample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    List<FreeBoard> freeBoards = new ArrayList<>();

    @OneToMany(mappedBy = "memberPostLikes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PostLikes> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "memberReplyLikes", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReplyLikes> replyLikes = new ArrayList<>();



    private Member(MemberBuilder memberBuilder) {
        this.username = memberBuilder.username;
        this.password = memberBuilder.password;
        this.role = memberBuilder.role;
    }

    public static MemberBuilder builder() {
        return new MemberBuilder();
    }

    public static class MemberBuilder {
        private String username;
        private String password;
        private MemberRoleEnum role;



        protected MemberBuilder() {

        }

        public MemberBuilder username(String username) {
            this.username = username;
            return this;
        }

        public MemberBuilder password(String password) {
            this.password = password;
            return this;
        }

        public MemberBuilder role(MemberRoleEnum role) {
            this.role = role;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

    public void adminUpdate(MemberRoleEnum memberRoleEnum) {
        this.role = memberRoleEnum;
    }


}

package hanghae11.springexample.entity;

import hanghae11.springexample.dto.FreeBoardEditRequestDto;
import hanghae11.springexample.member.dto.SignupRequestDto;
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


    public Member(String username, String password, MemberRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}

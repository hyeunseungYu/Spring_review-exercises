package hanghae11.springexample.member.dto;

import hanghae11.springexample.member.entity.Member;
import hanghae11.springexample.member.entity.MemberRoleEnum;
import lombok.Data;

@Data
public class memberDto {
    private Long id;
    private String username;
    private MemberRoleEnum role;

    public memberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.role = member.getRole();
    }
}

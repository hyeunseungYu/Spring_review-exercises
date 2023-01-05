package hanghae11.springexample.member.repository;

import hanghae11.springexample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRpository extends JpaRepository<Member, Long> {
    Member findByUsername (String username);
}

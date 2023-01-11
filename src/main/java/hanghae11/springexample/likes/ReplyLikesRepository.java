package hanghae11.springexample.likes;
import hanghae11.springexample.entity.Reply;
import hanghae11.springexample.entity.ReplyLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, Long> {
    Integer countByReplyLikes(Reply reply);
    boolean existsByUsername(String username);
    ReplyLikes findByUsername(String username);
}

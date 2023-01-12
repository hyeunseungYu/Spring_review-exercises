package hanghae11.springexample.likes;

import hanghae11.springexample.reply.entity.Reply;
import hanghae11.springexample.likes.entity.ReplyLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, Long> {
    Integer countByReplyLikes(Reply reply);

    boolean existsByUsername(String username);

    ReplyLikes findByUsername(String username);
}

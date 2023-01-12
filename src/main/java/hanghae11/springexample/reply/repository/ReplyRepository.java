package hanghae11.springexample.reply.repository;

import hanghae11.springexample.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}

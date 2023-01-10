package hanghae11.springexample.likes;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Likes;
import hanghae11.springexample.entity.Member;
import hanghae11.springexample.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Likes findByUsername(String username);

    Integer countByFreeBoardLikes(FreeBoard freeBoard);

    Integer countByReplyLikes(Reply reply);

}

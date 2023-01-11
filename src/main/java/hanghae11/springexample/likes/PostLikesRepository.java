package hanghae11.springexample.likes;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.PostLikes;
import hanghae11.springexample.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    PostLikes findByUsername(String username);

    Integer countByFreeBoardPostLikes(FreeBoard freeBoard);


    boolean existsByUsername(String username);

}

package hanghae11.springexample.likes;

import hanghae11.springexample.freeBoard.entity.FreeBoard;
import hanghae11.springexample.likes.entity.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    PostLikes findByUsername(String username);

    Integer countByFreeBoardPostLikes(FreeBoard freeBoard);

    boolean existsByUsername(String username);

}

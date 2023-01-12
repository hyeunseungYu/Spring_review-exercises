package hanghae11.springexample.freeBoard.repository;

import hanghae11.springexample.freeBoard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    List<FreeBoard> findAllByOrderByModifiedAtDesc();

}

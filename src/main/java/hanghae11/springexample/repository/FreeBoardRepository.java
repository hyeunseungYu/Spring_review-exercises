package hanghae11.springexample.repository;

import hanghae11.springexample.entity.FreeBoard;
import hanghae11.springexample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    List<FreeBoard> findAllByOrderByModifiedAtDesc();

}

package pro.sky3.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky3.example.model.BookCover;

import java.util.Optional;

public interface BookCoverRepository extends JpaRepository<BookCover, Long>{

    Optional<BookCover> findByBookId(Long bookId);
}

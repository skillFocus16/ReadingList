package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.domain.Book;

import java.util.List;

/**
 * Created by naaminicharles on 9/28/17.
 */
public interface ReadingListRepository extends JpaRepository<Book,Long> {

    List<Book> findByReader(String reader);

}

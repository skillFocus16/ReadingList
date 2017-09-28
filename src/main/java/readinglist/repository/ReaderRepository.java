package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.domain.Reader;

/**
 * Created by naaminicharles on 9/28/17.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {


}

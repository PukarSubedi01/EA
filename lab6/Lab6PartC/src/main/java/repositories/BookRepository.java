package repositories;

import domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("update Book b set b.locationCode = concat('BB', b.locationCode)")
    @Modifying
    void changeBookLocation();


    @Query("delete Book b where b.publicationYear < 1950")
    @Modifying
    void deleteBookByYear();

}

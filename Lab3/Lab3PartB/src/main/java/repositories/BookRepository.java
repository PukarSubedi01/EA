package repositories;

import domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
public interface BookRepository extends JpaRepository <Book, Long>{
    @Modifying
    @Query("update Book b set b.title= :title where b.id= :id")
    void updateBookTitle(@Param("title") String title, @Param("id") long id);

}

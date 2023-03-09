package repositories;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Long> {
    List<CD> findCdByArtist(String artist);
    List<CD> findCDSByArtistAndAndPriceIsLessThan(String artist,double price);
    @Query("select c from CD as c where c.artist = :artist and c.price > :price")
    List<CD> findCDByArtistAndPrice(@Param("artist") String artist, @Param("price") double price);

    @Query(value = "select * from CD where artist = 'artist1'", nativeQuery = true)
    List<CD> findCDByArtistNativeQuery();
}

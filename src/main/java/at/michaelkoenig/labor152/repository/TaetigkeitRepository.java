package at.michaelkoenig.labor152.repository;

import at.michaelkoenig.labor152.model.Taetigkeit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Michael König
 */
@Repository
public interface TaetigkeitRepository extends JpaRepository<Taetigkeit, Integer> {
    @Query("delete from Taetigkeit t where t.taetMitId = ?1")
    public void deleteAllByUserId(String empid);

    @Query("select t from Taetigkeit t where t.taetDatum = ?1")
    public List<Taetigkeit> findByDate(LocalDate date);

    @Query("select t from Taetigkeit t where t.taetDatum between ?1 and ?2")
    public List<Taetigkeit> findBetweenDates(LocalDate startdate, LocalDate enddate);

}

package at.michaelkoenig.labor152.repository;

import at.michaelkoenig.labor152.model.Mitarbeiter;
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
    public void deleteAllByTaetMitId(String empid);

    @Query("select t from Taetigkeit t where t.taetDatum = ?1")
    public List<Taetigkeit> findAllByTaetDatumEquals(LocalDate date);

    List<Taetigkeit> findAllByTaetMitIdEqualsAndTaetDatumBetween(String empid, LocalDate from, LocalDate to);
}

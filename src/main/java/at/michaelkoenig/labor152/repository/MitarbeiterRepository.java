package at.michaelkoenig.labor152.repository;

import at.michaelkoenig.labor152.model.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Michael König
 */
@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, String> {
    @Query("select sum(t.taetDauer) from Taetigkeit t where t.taetMitId = ?1")
    public Integer getTotalWorkingTimeById(String empid);
}

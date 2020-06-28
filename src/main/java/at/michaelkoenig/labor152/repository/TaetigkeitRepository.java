package at.michaelkoenig.labor152.repository;

import at.michaelkoenig.labor152.model.Taetigkeit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Michael König
 */
@Repository
public interface TaetigkeitRepository extends JpaRepository<Taetigkeit, Integer> {
    @Query("delete from Taetigkeit t where t.taetMitId = ?1")
    public void deleteAllByUserId(String empid);
}

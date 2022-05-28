package billennium.faculties.walkadog.infrastructure;

import billennium.faculties.walkadog.domain.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends JpaRepository<Walk,Long> {

    @Query(value = "SELECT * FROM WALK WHERE WALK_SLOTS_ID = ?1 LIMIT 1", nativeQuery = true)
    Walk findByWalkSlotsId(long id);
}

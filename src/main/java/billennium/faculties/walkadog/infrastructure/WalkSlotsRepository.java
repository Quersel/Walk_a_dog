package billennium.faculties.walkadog.infrastructure;

import billennium.faculties.walkadog.domain.WalkSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WalkSlotsRepository extends JpaRepository<WalkSlots,Long> {

    @Query(value = "SELECT COUNT(*) FROM WALK_SLOTS WHERE " +
            "?1 NOT BETWEEN START_DATE AND CAST(START_DATE + INTERVAL 3600 SECOND as DATETIME) AND" +
            "?2 NOT BETWEEN START_DATE AND CAST(START_DATE + INTERVAL 3600 SECOND as DATETIME) AND" +
            "TRAINER_ID = ?3 LIMIT 1", nativeQuery = true)
    int checkWalkSlotsCrossingWalkSlotsWhereTrenerId(LocalDateTime start, LocalDateTime end, Long id);

    @Query(value = "SELECT COUNT(*) FROM WALK_SLOTS WHERE REAL_DAY = ?1 AND TRAINER_ID = ?2", nativeQuery = true)
    int countWalkSlotsWhereDayAndTrainerId(LocalDate day, Long id);

    @Query(value = "SELECT * FROM WALK_SLOTS WHERE TRAINER_ID = ?1 AND START_DATE > ?2", nativeQuery = true)
    List<WalkSlots> findAllByTrainerIdAndNewerThan(Long id, LocalDateTime localDateTime);
}

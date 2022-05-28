package billennium.faculties.walkadog.infrastructure;

import billennium.faculties.walkadog.domain.WalkReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkReviewRepository extends JpaRepository<WalkReview,Long> {
}

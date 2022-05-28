package billennium.faculties.walkadog.application.dto;

import billennium.faculties.walkadog.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class WalkSlotsDto {
    private LocalDate realDay;
    private LocalDateTime startDate;
    private Long trainerId;
}

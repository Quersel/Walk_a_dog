package billennium.faculties.walkadog.application.dto;

import billennium.faculties.walkadog.domain.Pets;
import billennium.faculties.walkadog.domain.WalkSlots;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class WalkDto {
    private WalkSlots walkSlots;
    private List<Pets> pets;
}

package billennium.faculties.walkadog.application;

import billennium.faculties.walkadog.application.dto.WalkDto;
import billennium.faculties.walkadog.domain.Walk;
import billennium.faculties.walkadog.domain.WalkSlots;
import billennium.faculties.walkadog.domain.enums.WalkStatus;
import billennium.faculties.walkadog.infrastructure.WalkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WalkService {
    private final WalkRepository walkRepository;
    //private WalkSlotsRepository walkSlotsRepository;

    public String insertWalk(WalkDto walkDto) {
        //if(walkRepository.findByWalkSlotsId(walk.getWalkSlots().getId()) == null){
        if (walkDto.getWalkSlots() == null) {
            Walk walk = Walk.builder()
                    .walkSlots(walkDto.getWalkSlots())
                    .walkStatus(WalkStatus.PLANNED)
                    .pets(walkDto.getPets())
                    .build();
            walkRepository.save(walk);

            return "Walk added correctly";
        }

        throw new IllegalStateException("There is no walk slot with this Id");
    }

    public String deleteWalk(Long walkId) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        Walk walk = walkRepository.findById(Long.valueOf(walkId)).orElse(null);

        if (walk != null) {
            WalkSlots walkSlots = walk.getWalkSlots();
            if (localDateTime.isBefore(walkSlots.getStartDate())) {
                walkRepository.delete(walk);

                return "Walk removed";
            }

            throw new IllegalStateException("There is no walk with this id");
        }

        throw new IllegalStateException("You can only remove walks more than 24h before");
    }

    public String startWalk(Long walkId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Walk walk = walkRepository.findById(Long.valueOf(walkId)).orElse(null);

        if (walk != null) {
            WalkSlots walkSlots = walk.getWalkSlots();
            if (!localDateTime.isBefore(walkSlots.getStartDate()) &&
                    !localDateTime.isAfter(walkSlots.getStartDate().plusHours(1))) {
                walk.setWalkStatus(WalkStatus.IN_PROGRESS);
                walkRepository.save(walk);

                return "Walk started!";
            }

            throw new IllegalStateException("There is no walk with this id");
        }

        throw new IllegalStateException("Walk is blocked");
    }

    public String cancelWalk(Long walkId) {
        Walk walk = walkRepository.findById(Long.valueOf(walkId)).orElse(null);

        if (walk != null) {
            walk.setWalkStatus(WalkStatus.CANCELLED);
            walkRepository.save(walk);

            return "Walk cancelled";
        }

        throw new IllegalStateException("Walk doesnt exist");
    }

    public String finishWalk(Long walkId) {
        Walk walk = walkRepository.findById(Long.valueOf(walkId)).orElse(null);
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(1);

        if (walk != null) {
            WalkSlots walkSlots = walk.getWalkSlots();
            if (!localDateTime.isBefore(walkSlots.getStartDate())) {
                walk.setWalkStatus(WalkStatus.FINISHED);
                walkRepository.save(walk);

                return "Walk finished";
            }

            throw new IllegalStateException("Walk doesnt exist");
        }

        throw new IllegalStateException("You cannot finish your walk right now");
    }
}

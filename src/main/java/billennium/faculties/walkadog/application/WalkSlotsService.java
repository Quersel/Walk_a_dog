package billennium.faculties.walkadog.application;

import billennium.faculties.walkadog.application.dto.WalkSlotsDto;
import billennium.faculties.walkadog.domain.Trainer;
import billennium.faculties.walkadog.domain.Walk;
import billennium.faculties.walkadog.domain.WalkSlots;
import billennium.faculties.walkadog.infrastructure.TrainerRepository;
import billennium.faculties.walkadog.infrastructure.WalkRepository;
import billennium.faculties.walkadog.infrastructure.WalkSlotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
@AllArgsConstructor
public class WalkSlotsService {
    private WalkSlotsRepository walkSlotsRepository;
    private TrainerRepository trainerRepository;
    private WalkRepository walkRepository;

    public String insertWalkSlots(WalkSlotsDto walkSlotsDto){
        boolean ifTrainerExist = trainerRepository.findById(walkSlotsDto.getTrainerId()).isPresent();
        if(!ifTrainerExist){
            throw new IllegalStateException("Trainer not found");
        }
        LocalDate realDay = walkSlotsDto.getRealDay();
        Long trainerId = walkSlotsDto.getTrainerId();

        if(walkSlotsRepository.countWalkSlotsWhereDayAndTrainerId(realDay, trainerId) < 5){
            LocalDateTime startDate = walkSlotsDto.getStartDate();
            if(walkSlotsRepository.checkWalkSlotsCrossingWalkSlotsWhereTrenerId(startDate, startDate.plusHours(1), trainerId) == 1)


            if((isWeekend(realDay) &&
                    !startDate.isAfter(LocalDateTime.of(realDay, LocalTime.of(16, 0))) &&
                    !startDate.isBefore(LocalDateTime.of(realDay, LocalTime.of(9, 0)))) ||
                    (!startDate.isAfter(LocalDateTime.of(realDay, LocalTime.of(18, 0))) &&
                            !startDate.isBefore(LocalDateTime.of(realDay, LocalTime.of(8, 0))))
            ){
                Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(()-> new IllegalStateException("Not found"));
                WalkSlots walkSlots = WalkSlots.builder()
                        .realDay(walkSlotsDto.getRealDay())
                        .startDate(walkSlotsDto.getStartDate())
                        .trainer(trainer)
                        .build();
                walkSlotsRepository.save(walkSlots);
                return "Walk added correctly";
            }

            throw new IllegalStateException("No slots left");
        }
        throw new IllegalStateException("Slots planning conflict");
    }

    private static boolean isWeekend(final LocalDate ld)
    {
        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

    public String deleteWalkSlots(Long slotId) {
        WalkSlots walkSlots = walkSlotsRepository.findById(Long.valueOf(slotId)).orElse(null);
        if(walkSlots != null){
            Walk walk = walkRepository.findByWalkSlotsId(walkSlots.getId());
            if(walk == null){
                walkSlotsRepository.delete(walkSlots);
                return "Slot removed";
            }

            throw new IllegalStateException("There is not slot with this ID");
        }

        throw new IllegalStateException("Current slot has active walk, remove it before continue");
    }

    public List<WalkSlots> readPresentWalkSlots(Long trainerId){
        LocalDateTime localDateTime = LocalDateTime.now();
        return walkSlotsRepository.findAllByTrainerIdAndNewerThan(trainerId, localDateTime);
    }

    public int passWalkSlots(Long slotId, Long trainerId){
        WalkSlots walkSlots = walkSlotsRepository.findById(Long.valueOf(slotId)).orElse(null);
        if(walkSlots != null) {
            LocalDateTime localDateTime = walkSlots.getStartDate();
            LocalDate localDate = walkSlots.getRealDay();
            if(walkSlotsRepository.checkWalkSlotsCrossingWalkSlotsWhereTrenerId(localDateTime, localDateTime.plusHours(1), trainerId) == 0 &&
            walkSlotsRepository.countWalkSlotsWhereDayAndTrainerId(localDate, trainerId) < 5){

                return 2;
            }

            return 1;
        }

        return 0;
    }
    //TODO PASSWALKSLOTS FUNCTIONALITY
}

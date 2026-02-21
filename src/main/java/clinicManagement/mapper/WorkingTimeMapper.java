package clinicManagement.mapper;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.WorkingTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class WorkingTimeMapper {
    public WorkingTimeEntity toWorkingTimeEntity(WorkingTimeDto workingTimeDto, DoctorEntity doctorEntity){
        return WorkingTimeEntity.builder()
                .doctorEntity(doctorEntity)
                .dayOfWeek(workingTimeDto.getDayOfWeek())
                .weekStart(workingTimeDto.getWeekStart())
                .startTime(workingTimeDto.getStartTime())
                .endTime(workingTimeDto.getEndTime())
                .build();
    }

}

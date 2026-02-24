package clinicManagement.mapper;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.WorkingTimeResponseDto;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.WorkingTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Component
public class WorkingTimeMapper {
    public WorkingTimeEntity toWorkingTimeEntity(WorkingTimeDto workingTimeDto, DoctorEntity doctorEntity){
        return WorkingTimeEntity.builder()
                .doctorEntity(doctorEntity)
                .dayOfWeek(workingTimeDto.getDayOfWeek())
                .workingDate(workingTimeDto.getWorkingDate())
                .startTime(workingTimeDto.getStartTime())
                .endTime(workingTimeDto.getEndTime())
                .isExpired(true)
                .build();
    }
    public WorkingTimeResponseDto toWorkingTimeResponseDto(WorkingTimeEntity workingTimeEntity){
        return WorkingTimeResponseDto.builder()
                .doctorId(workingTimeEntity.getDoctorEntity().getId())
                .dayOfWeek(workingTimeEntity.getDayOfWeek())
                .workingDate(workingTimeEntity.getWorkingDate())
                .startTime(workingTimeEntity.getStartTime())
                .endTime(workingTimeEntity.getEndTime())
                .isExpired(workingTimeEntity.getIsExpired())
                .build();
    }
}

package clinicManagement.mapper;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.WorkingTimeResponseDto;
import clinicManagement.entity.GlobalSettingsEntity;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.WorkingTimeEntity;
import clinicManagement.service.GlobalSettingsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class WorkingTimeMapper {
    private final GlobalSettingsService globalSettingsService;

    public WorkingTimeMapper(GlobalSettingsService globalSettingsService) {
        this.globalSettingsService = globalSettingsService;
    }

    public WorkingTimeEntity toWorkingTimeEntity(WorkingTimeDto workingTimeDto, DoctorEntity doctorEntity){
        return WorkingTimeEntity.builder()
                .doctorEntity(doctorEntity)
                .dayOfWeek(workingTimeDto.getDayOfWeek())
                .workingDate(workingTimeDto.getWorkingDate())
                .startTime(workingTimeDto.getStartTime())
                .endTime(workingTimeDto.getEndTime())
                .breakStart(globalSettingsService.getBreakStartTime())
                .breakEnd(globalSettingsService.getBreakEndTime())
                .reason(globalSettingsService.getReason())
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
                .breakStart(globalSettingsService.getBreakStartTime())
                .breakEnd(globalSettingsService.getBreakEndTime())
                .reason(globalSettingsService.getReason())
                .isExpired(workingTimeEntity.getIsExpired())
                .build();
    }
}

package clinicManagement.service.impl;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.TimeSlotResponseDto;
import clinicManagement.entity.AppointmentEntity;
import clinicManagement.entity.WorkingTimeEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.repository.AppointmentRepository;
import clinicManagement.repository.WorkingTimeRepository;
import clinicManagement.service.GlobalSettingsService;
import clinicManagement.service.TimeSlotService;
import clinicManagement.util.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {
    private final WorkingTimeRepository workingTimeRepository;
    private final GlobalSettingsService globalSettingsService;
    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> getTimeSlotsByDoctorId(Long doctorId, LocalDate date) {
      WorkingTimeEntity workingTimeEntity = workingTimeRepository.findByDoctorEntity_IdAndWorkingDate(doctorId,date)
              .orElseThrow(()-> new AppBadException("not marked working day or this day holiday"));

        LocalTime current = workingTimeEntity.getStartTime();
        LocalTime workEnd = workingTimeEntity.getEndTime();
        LocalTime breakStart = workingTimeEntity.getBreakStart();
        LocalTime breakEnd = workingTimeEntity.getBreakEnd();
        Integer slotDuration = globalSettingsService.getSlotDuration();

        List<AppointmentEntity> appointmentEntity = appointmentRepository.findAllByDoctorEntity_IdAndAppointmentDate(doctorId,date);
        List<LocalTime> bookedStartTime = appointmentEntity.stream().map(AppointmentEntity::getStartTime).toList();
        List<TimeSlotResponseDto> slotResponseDtoList = new ArrayList<>();
        while (current.isBefore(workEnd)){
            LocalTime slotEnd = current.plusMinutes(slotDuration);
            if (current.isBefore(breakStart) && Duration.between(slotEnd, breakStart).toMinutes() < slotDuration) {
                slotEnd = breakStart;
            }

            if (current.isAfter(breakEnd) && Duration.between(slotEnd, workEnd).toMinutes() < slotDuration) {
                slotEnd = workEnd;
            }

            AppointmentStatus status;               // bt 12  13  ;       11
            if (!current.isBefore(breakStart) && current.isBefore(breakEnd)){
                status = AppointmentStatus.BREAK;
                slotEnd = breakEnd;
            }else if (bookedStartTime.contains(current)){
                status=AppointmentStatus.BOOKED;
            }else {
                status = AppointmentStatus.AVAILABLE;
            }
         slotResponseDtoList.add(TimeSlotResponseDto.builder()
                            .startTime(current)
                            .endTime(slotEnd)
                            .status(status)
                            .build());
            current = slotEnd;
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("getting slots",true,slotResponseDtoList,200));
    }
}






























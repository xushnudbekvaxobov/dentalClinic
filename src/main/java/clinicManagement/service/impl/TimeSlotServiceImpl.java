package clinicManagement.service.impl;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.TimeSlotResponseDto;
import clinicManagement.entity.AppointmentEntity;
import clinicManagement.entity.GlobalSettingsEntity;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {
    private final WorkingTimeRepository workingTimeRepository;
    private final GlobalSettingsService globalSettingsService;
    private final AppointmentRepository appointmentRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> getTimeSlotsByDoctorId(UUID doctorId, LocalDate date) {
      WorkingTimeEntity workingTimeEntity = workingTimeRepository.findByDoctorEntity_IdAndWorkingDate(doctorId,date)
              .orElseThrow(()-> new AppBadException("not marked working day or this day holiday"));
        List<AppointmentEntity> appointmentEntity = appointmentRepository.findAllByDoctorEntity_IdAndAppointmentDate(doctorId,date);
        List<LocalTime> bookedStartTime = appointmentEntity.stream().map(AppointmentEntity::getStartTime).toList();
        List<TimeSlotResponseDto> slotResponseDtoList = generateSlot(workingTimeEntity,bookedStartTime);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("getting slots",true,slotResponseDtoList,200));
    }

    private List<TimeSlotResponseDto> generateSlot(WorkingTimeEntity workingTimeEntity,
                                                   List<LocalTime> bookedStartTime){
        LocalTime current = workingTimeEntity.getStartTime();
        int slotDuration = globalSettingsService.getSlotDuration();
        LocalTime breakStart = workingTimeEntity.getBreakStart();
        LocalTime breakEnd = workingTimeEntity.getBreakEnd();
        LocalTime endTime = workingTimeEntity.getEndTime();

// ...existing code...
        AppointmentStatus status;
        List<TimeSlotResponseDto> timeSlotResponseDtoList = new ArrayList<>();
        while(current.isBefore(endTime)){
            LocalTime slotEnd = current.plusMinutes(slotDuration);
            if(current.isBefore(breakStart) && Duration.between(slotEnd,breakStart).toMinutes() < slotDuration){
                slotEnd = breakStart;
            }
            if(!current.isBefore(breakEnd) && Duration.between(slotEnd,endTime).toMinutes() < slotDuration){
                slotEnd = endTime;
            }
            if(!current.isBefore(breakStart) && !slotEnd.isAfter(breakEnd)) {
                slotEnd = breakEnd;
                status = AppointmentStatus.BREAK;
            }else if(bookedStartTime.contains(current)){
                status = AppointmentStatus.BOOKED;
            }
            else {
                status = AppointmentStatus.AVAILABLE;
            }
            timeSlotResponseDtoList.add(new TimeSlotResponseDto(current,slotEnd,status));
            current = slotEnd;
        }
        return timeSlotResponseDtoList;
    }
}
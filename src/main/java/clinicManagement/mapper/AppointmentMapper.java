package clinicManagement.mapper;

import clinicManagement.dto.responseDto.AppointmentResponseDto;
import clinicManagement.entity.AppointmentEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public AppointmentResponseDto toAppointmentResponseDto(AppointmentEntity appointmentEntity,Long doctorId){
        return AppointmentResponseDto.builder()
                .doctorId(doctorId)
                .patientId(appointmentEntity.getPatientEntity().getId())
                .appointmentDate(appointmentEntity.getAppointmentDate())
                .startTime(appointmentEntity.getStartTime())
                .endTime(appointmentEntity.getEndTime())
                .status(appointmentEntity.getStatus())
                .build();
    }
}

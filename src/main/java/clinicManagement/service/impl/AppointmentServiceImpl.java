package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.AppointmentDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.AppointmentResponseDto;
import clinicManagement.entity.AppointmentEntity;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.PatientEntity;
import clinicManagement.entity.WorkingTimeEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.mapper.AppointmentMapper;
import clinicManagement.repository.AppointmentRepository;
import clinicManagement.repository.DoctorRepository;
import clinicManagement.repository.PatientRepository;
import clinicManagement.repository.WorkingTimeRepository;
import clinicManagement.service.AppointmentService;
import clinicManagement.util.enums.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final WorkingTimeRepository workingTimeRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public ResponseEntity<ApiResponse<?>> booking(AppointmentDto appointmentDto,Long patientId,Long doctorId) {
       WorkingTimeEntity workingTimeEntity = workingTimeRepository.findByDoctorEntity_IdAndWorkingDate(doctorId,appointmentDto.getAppointmentDate()).orElseThrow(()->new AppBadException("not working time"));
       if(!appointmentDto.getStartTime().isBefore(workingTimeEntity.getBreakStart()) && !appointmentDto.getEndTime().isAfter(workingTimeEntity.getBreakEnd())){
           throw new AppBadException("break time");
       }
       if(appointmentRepository.existsByDoctorEntity_IdAndAppointmentDateAndStartTime(doctorId,appointmentDto.getAppointmentDate(),appointmentDto.getStartTime())){
           throw new AppBadException("booked time");
       }

       DoctorEntity doctorEntity = doctorRepository.findById(doctorId).orElseThrow(()-> new AppBadException("doctor not found"));
       PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(()-> new AppBadException("patient not found"));
       AppointmentEntity appointmentEntity = AppointmentEntity.builder()
               .doctorEntity(doctorEntity)
               .patientEntity(patientEntity)
               .appointmentDate(appointmentDto.getAppointmentDate())
               .startTime(appointmentDto.getStartTime())
               .endTime(appointmentDto.getEndTime())
               .status(AppointmentStatus.BOOKED)
               .build();
       try {
           appointmentRepository.save(appointmentEntity);
       }catch (ConstraintViolationException e){
           throw new AppBadException("this slot already booked");
       }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("booked",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllAppointmentsByDoctorId(Long doctorId) {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAllByDoctorEntity_Id(doctorId);
        List<AppointmentResponseDto> responseDtoList = appointmentEntityList.stream().map(appointmentEntity -> appointmentMapper.toAppointmentResponseDto(appointmentEntity, doctorId)).toList();
       return ResponseEntity
               .status(HttpStatus.OK)
                .body(new ApiResponse<>("success",true,responseDtoList,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate date) {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAllByDoctorEntity_IdAndAppointmentDate(doctorId,date);
        List<AppointmentResponseDto> responseDtoList = appointmentEntityList.stream().map(appointmentEntity -> appointmentMapper.toAppointmentResponseDto(appointmentEntity, doctorId)).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("success",true,responseDtoList,200));
    }

}

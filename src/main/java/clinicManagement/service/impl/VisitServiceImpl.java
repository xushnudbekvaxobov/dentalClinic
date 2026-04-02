package clinicManagement.service.impl;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.PatientEntity;
import clinicManagement.entity.VisitEntity;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.repository.DoctorRepository;
import clinicManagement.repository.PatientRepository;
import clinicManagement.repository.VisitRepository;
import clinicManagement.service.VisitService;
import clinicManagement.util.enums.VisitStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> createVisit(UUID patientId, UUID doctorId) {
        PatientEntity patientEntity = patientRepository.findById(patientId).orElseThrow(()-> new DataNotFoundException("patient not found with patientId: " + patientId));
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId).orElseThrow(()-> new DataNotFoundException("doctor not found with doctorId: "+ doctorId));
        VisitEntity.builder()
                .patientEntity(patientEntity)
                .doctorEntity(doctorEntity)
                .appointmentEntity(null)
                .isWalkIn(true)
                .startedAt(null)
                .endedAt(null)
                .queueNumber(generateQueueNumber())
                .notes(null)
                .status(VisitStatus.WAITING)
                .build();
        return null;
    }

    private String generateQueueNumber(){
        Integer maxRow = visitRepository.findMaxRowQueueNumberByVisitDate(LocalDate.now());
        return String.format("%03d", maxRow + 1);
    }
}

package clinicManagement.mapper;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.entity.PatientEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.util.enums.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
public class PatientMapper {
//    public PatientEntity toPatientEntity(PatientDto patientDto){
//        PatientEntity patientEntity = new PatientEntity();
//        return toEntity(patientEntity, patientDto);
//    }

    public PatientEntity toEntity(PatientDto patientDto, UserEntity user) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName(patientDto.getFirstName());
        patientEntity.setLastName(patientDto.getLastName());
        patientEntity.setBirthDate(patientDto.getBirthDate());
        patientEntity.setGender(patientDto.getGender());
        patientEntity.setPhone(patientDto.getPhone());
        patientEntity.setAddress(patientDto.getAddress());
        patientEntity.setCreatedAt(LocalDateTime.now());
        patientEntity.setAllergies(patientDto.getAllergies());
        patientEntity.setUser(user);
        return patientEntity;
    }


}

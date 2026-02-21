package clinicManagement.mapper;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.entity.PatientEntity;
import clinicManagement.util.enums.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
public class PatientMapper {
    public PatientEntity toPatientEntity(PatientDto patientDto){
        PatientEntity patientEntity = new PatientEntity();
        return toEntity(patientEntity, patientDto);
    }
    public PatientEntity toPatientEntityForUpdate(PatientEntity patientEntity,PatientDto patientDto){
        return toEntity(patientEntity, patientDto);
    }

    private PatientEntity toEntity(PatientEntity patientEntity, PatientDto patientDto) {
        patientEntity.setFullName(patientDto.getFullName());
        patientEntity.setBirthDate(patientDto.getBirthDate());
        patientEntity.setGender(patientDto.getGender());
        patientEntity.setPhone(patientDto.getPhone());
        patientEntity.setAddress(patientDto.getAddress());
        patientEntity.setCreatedAt(LocalDate.now());
        patientEntity.setAllergies(patientDto.getAllergies());
        return patientEntity;
    }


}

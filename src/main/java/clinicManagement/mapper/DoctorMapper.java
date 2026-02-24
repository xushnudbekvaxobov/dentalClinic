package clinicManagement.mapper;

import clinicManagement.dto.requestDto.DoctorDto;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.util.enums.DoctorStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class DoctorMapper {
    public DoctorEntity toDoctorEntity(DoctorDto doctorDto, EmployeeEntity employeeEntity){
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setEmployeeEntity(employeeEntity);
        doctorEntity.setSpeciality(doctorDto.getSpeciality());
        doctorEntity.setExperienceYear(doctorDto.getExperienceYear());
        doctorEntity.setLicenseNumber(doctorDto.getLicenseNumber());
        doctorEntity.setStatus(DoctorStatus.OFFLINE);
        return doctorEntity;
    }
}

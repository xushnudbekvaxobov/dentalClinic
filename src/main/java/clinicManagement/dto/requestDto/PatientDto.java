package clinicManagement.dto.requestDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {
    public String fullName;
    public LocalDate birthDate;
    public String gender;
    public String phone;
    public String address;
    public String allergies;
}

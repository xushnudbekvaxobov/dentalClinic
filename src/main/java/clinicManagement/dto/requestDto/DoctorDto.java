package clinicManagement.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDto {
    @NotBlank(message = "speciality is can't empty")
    private String speciality;
    @NotNull(message = "experience year can't empty")
    private Integer experienceYear;
    @NotBlank(message = "licenseNumber is can't empty")
    private String licenseNumber;
}

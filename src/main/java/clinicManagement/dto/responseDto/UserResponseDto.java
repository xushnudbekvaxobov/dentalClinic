package clinicManagement.dto.responseDto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {
    private UUID id;
    private String email;
    private String status;
}

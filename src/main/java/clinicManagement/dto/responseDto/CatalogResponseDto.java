package clinicManagement.dto.responseDto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogResponseDto {
    private String name;
    private String description;
    private BigDecimal basePrice;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
}

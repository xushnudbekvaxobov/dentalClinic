package clinicManagement.dto.requestDto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogDto {
    private String name;
    private String description;
    private BigDecimal basePrice;
}

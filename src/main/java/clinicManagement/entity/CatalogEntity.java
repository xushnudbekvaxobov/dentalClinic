package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "catalogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private BigDecimal basePrice;
    @Column(nullable = false)
    private Boolean isActive;

}

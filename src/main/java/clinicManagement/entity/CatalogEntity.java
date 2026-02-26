package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private BigDecimal basePrice;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate updatedAt;
    @Column(nullable = false)
    private Boolean isActive;

}

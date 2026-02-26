package clinicManagement.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public String fullName;
    @Column(nullable = false)
    public LocalDate birthDate;
    @Column(nullable = false)
    public String gender;
    @Column(nullable = false)
    public String phone;
    @Column(nullable = false)
    public String address;
    @Column(updatable = false)
    public LocalDate createdAt;

}

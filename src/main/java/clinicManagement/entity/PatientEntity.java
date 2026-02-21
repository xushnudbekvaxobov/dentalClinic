package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntity extends BaseEntity {
    private String allergies;
}

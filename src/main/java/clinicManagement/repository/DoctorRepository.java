package clinicManagement.repository;

import clinicManagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<DoctorEntity,UUID> {
    Boolean existsByEmployeeEntity_Id(UUID employeeEntityId);
    Optional<DoctorEntity> findAllById(UUID id);

    Optional<DoctorEntity> findByEmployeeEntity_Id(UUID employeeEntityId);

}

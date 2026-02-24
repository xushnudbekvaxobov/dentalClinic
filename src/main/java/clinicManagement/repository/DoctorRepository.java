package clinicManagement.repository;

import clinicManagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {
    Boolean existsByEmployeeEntity_Id(Long employeeEntityId);
    Optional<DoctorEntity> findAllById(Long id);

    Optional<DoctorEntity> findByEmployeeEntity_Id(Long employeeEntityId);

}

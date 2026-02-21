package clinicManagement.repository;

import clinicManagement.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatentRepository extends JpaRepository<PatientEntity,Long>, JpaSpecificationExecutor<PatientEntity> {
    Page<PatientEntity> findAll(Pageable pageable);
}

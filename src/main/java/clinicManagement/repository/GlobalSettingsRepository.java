package clinicManagement.repository;

import clinicManagement.entity.GlobalSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalSettingsRepository extends JpaRepository<GlobalSettingsEntity,Long> {
}

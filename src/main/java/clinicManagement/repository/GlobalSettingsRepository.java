package clinicManagement.repository;

import clinicManagement.entity.GlobalSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GlobalSettingsRepository extends JpaRepository<GlobalSettingsEntity, UUID> {
  Optional<GlobalSettingsEntity> findTopBy();
}

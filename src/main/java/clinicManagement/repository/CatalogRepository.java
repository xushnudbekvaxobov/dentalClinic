package clinicManagement.repository;

import clinicManagement.entity.CatalogEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {
    @Override
    Page<CatalogEntity> findAll(@NonNull Pageable pageable);
}

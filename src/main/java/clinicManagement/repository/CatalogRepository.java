package clinicManagement.repository;

import clinicManagement.entity.CatalogEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogEntity,Long> {
    @Override
    Page<CatalogEntity> findAll(@NonNull Pageable pageable);
}

package clinicManagement.mapper;

import clinicManagement.dto.requestDto.CatalogDto;
import clinicManagement.dto.responseDto.CatalogResponseDto;
import clinicManagement.entity.CatalogEntity;
import org.springframework.stereotype.Component;

@Component
public class CatalogMapper {
    public CatalogEntity toCatalogEntity(CatalogDto catalogDto){
        return CatalogEntity.builder()
                .name(catalogDto.getName())
                .description(catalogDto.getDescription())
                .basePrice(catalogDto.getBasePrice())
                .isActive(true)
                .build();
    }
    public CatalogResponseDto toCatalogResponseDto(CatalogEntity catalogEntity){
        return CatalogResponseDto.builder()
                .name(catalogEntity.getName())
                .description(catalogEntity.getDescription())
                .basePrice(catalogEntity.getBasePrice())
                .createdAt(catalogEntity.getCreatedAt())
                .updatedAt(catalogEntity.getUpdatedAt())
                .isActive(catalogEntity.getIsActive())
                .build();
    }
}

package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.CatalogDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.CatalogResponseDto;
import clinicManagement.entity.CatalogEntity;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.CatalogMapper;
import clinicManagement.repository.CatalogRepository;
import clinicManagement.service.CatalogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogMapper catalogMapper;
    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogMapper catalogMapper, CatalogRepository catalogRepository) {
        this.catalogMapper = catalogMapper;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> createCatalog(CatalogDto catalogDto) {
        catalogRepository.save(catalogMapper.toCatalogEntity(catalogDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("catalog created",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateCatalog(Long catalogId, CatalogDto catalogDto) {
        CatalogEntity catalogEntity = catalogRepository.findById(catalogId).orElseThrow(()->new DataNotFoundException("catalog not found"));
            catalogEntity.setName(catalogDto.getName());
            catalogEntity.setDescription(catalogDto.getDescription());
            catalogEntity.setBasePrice(catalogDto.getBasePrice());
            catalogRepository.save(catalogEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("catalog updated",true,null,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllCatalog(int page,int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<CatalogEntity> catalogEntityPage = catalogRepository.findAll(pageRequest);
        Page<CatalogResponseDto> catalogResponseDtoPage = catalogEntityPage.map(catalogMapper::toCatalogResponseDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("all catalog",true,catalogResponseDtoPage,200));
    }

}

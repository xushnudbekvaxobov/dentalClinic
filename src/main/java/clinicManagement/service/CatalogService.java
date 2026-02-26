package clinicManagement.service;

import clinicManagement.dto.requestDto.CatalogDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CatalogService {
    ResponseEntity<ApiResponse<?>> createCatalog(CatalogDto catalogDto);
    ResponseEntity<ApiResponse<?>> updateCatalog(Long catalogId,CatalogDto catalogDto);
    ResponseEntity<ApiResponse<?>> getAllCatalog(int page,int size);
}

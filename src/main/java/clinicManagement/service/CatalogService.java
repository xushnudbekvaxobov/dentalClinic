package clinicManagement.service;

import clinicManagement.dto.requestDto.CatalogDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CatalogService {
    ResponseEntity<ApiResponse<?>> createCatalog(CatalogDto catalogDto);
    ResponseEntity<ApiResponse<?>> updateCatalog(UUID catalogId,CatalogDto catalogDto);
    ResponseEntity<ApiResponse<?>> getAllCatalog(int page,int size);
}

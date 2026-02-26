package clinicManagement.controller;

import clinicManagement.dto.requestDto.CatalogDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> createCatalog(@RequestBody CatalogDto catalogDto){
        return catalogService.createCatalog(catalogDto);
    }

    @PutMapping("/update/{catalogId}")
    public ResponseEntity<ApiResponse<?>> updateCatalog(@PathVariable Long catalogId,@RequestBody CatalogDto catalogDto){
        return catalogService.updateCatalog(catalogId,catalogDto);
    }
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<?>> getAllCatalog(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size){
        return catalogService.getAllCatalog(page,size);
    }
}

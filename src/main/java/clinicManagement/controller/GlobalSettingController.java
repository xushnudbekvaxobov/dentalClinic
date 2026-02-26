package clinicManagement.controller;

import clinicManagement.dto.requestDto.GlobalSettingDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.GlobalSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/global-setting")
@RequiredArgsConstructor
public class GlobalSettingController {

    private final GlobalSettingsService globalSettingsService;

    @GetMapping("/global-settings")
    public ResponseEntity<ApiResponse<?>> getSetting(){
        return globalSettingsService.getGlobalSetting();
    }

    @PostMapping("/create-setting")
    public ResponseEntity<ApiResponse<?>> createSetting(@RequestBody GlobalSettingDto globalSettingDto){
        return globalSettingsService.addGlobalSetting(globalSettingDto);
    }
    @PutMapping("/update-setting")
    public ResponseEntity<ApiResponse<?>> updateSetting(@RequestBody GlobalSettingDto globalSettingDto){
        return globalSettingsService.addGlobalSetting(globalSettingDto);
    }
}

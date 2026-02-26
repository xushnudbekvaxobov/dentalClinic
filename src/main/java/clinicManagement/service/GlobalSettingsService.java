package clinicManagement.service;

import clinicManagement.dto.requestDto.GlobalSettingDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.GlobalSettingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public interface GlobalSettingsService {
    ResponseEntity<ApiResponse<?>> addGlobalSetting(GlobalSettingDto globalSettingDto);
    ResponseEntity<ApiResponse<?>> getGlobalSetting();
    LocalTime getBreakStartTime();
    LocalTime getBreakEndTime();
    String getReason();
    Integer getSlotDuration();
}

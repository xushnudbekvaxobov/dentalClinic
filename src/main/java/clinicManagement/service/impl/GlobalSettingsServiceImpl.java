package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.GlobalSettingDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.GlobalSettingResponseDto;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.GlobalSettingsEntity;
import clinicManagement.entity.WorkingTimeEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.mapper.GlobalSettingMapper;
import clinicManagement.repository.DoctorRepository;
import clinicManagement.repository.GlobalSettingsRepository;
import clinicManagement.repository.WorkingTimeRepository;
import clinicManagement.service.GlobalSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalSettingsServiceImpl implements GlobalSettingsService {
    private final GlobalSettingsRepository globalSettingsRepository;
    private final GlobalSettingMapper globalSettingMapper;
    private final DoctorRepository doctorRepository;
    private final WorkingTimeRepository workingTimeRepository;

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> addGlobalSetting(GlobalSettingDto globalSettingDto) {
        globalSettingsRepository.save(globalSettingMapper.toGlobalSettingsEntity(globalSettingDto));
        List<WorkingTimeEntity> doctorEntity = workingTimeRepository.findAll();
        doctorEntity.forEach(wt -> {
            wt.setBreakStart(globalSettingDto.getBreakStart());
            wt.setBreakEnd(globalSettingDto.getBreakEnd());
        });
        workingTimeRepository.saveAll(doctorEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("global setting created",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getGlobalSetting() {
              GlobalSettingsEntity globalSettingsEntity = globalSettingsRepository.findById(1L).orElseThrow(()->new AppBadException("global settings not found"));
               return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("global settings",true,globalSettingMapper.toGlobalSettingResponseDto(globalSettingsEntity),200));
    }

    @Override
    public LocalTime getBreakStartTime() {
       GlobalSettingsEntity globalSettingsEntity = globalSettingsRepository.findById(1L).orElseThrow(() -> new AppBadException("global settings not found"));
        return globalSettingsEntity.getBreakStart();
    }

    @Override
    public LocalTime getBreakEndTime() {
        GlobalSettingsEntity globalSettingsEntity = globalSettingsRepository.findById(1L).orElseThrow(() -> new AppBadException("global settings not found"));
        return globalSettingsEntity.getBreakEnd();
    }

    @Override
    public Integer getSlotDuration() {
        GlobalSettingsEntity globalSettingsEntity = globalSettingsRepository.findById(1L).orElseThrow(() -> new AppBadException("global settings not found"));
        return globalSettingsEntity.getSlotDuration();
    }


    @Override
    public String getReason() {
        GlobalSettingsEntity globalSettingsEntity = globalSettingsRepository.findById(1L).orElseThrow(() -> new AppBadException("global settings not found"));
        return globalSettingsEntity.getReason();
    }
}

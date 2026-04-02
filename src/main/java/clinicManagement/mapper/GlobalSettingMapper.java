package clinicManagement.mapper;

import clinicManagement.dto.requestDto.GlobalSettingDto;
import clinicManagement.dto.responseDto.GlobalSettingResponseDto;
import clinicManagement.entity.GlobalSettingsEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
@Setter
public class GlobalSettingMapper {
    public GlobalSettingsEntity toGlobalSettingsEntity(GlobalSettingDto globalSettingDto, GlobalSettingsEntity globalSettingsEntity1) {
        GlobalSettingsEntity globalSettingsEntity = new GlobalSettingsEntity();
        if(globalSettingsEntity1 != null) {
            globalSettingsEntity = globalSettingsEntity1;
        }
        globalSettingsEntity.setBreakStart(globalSettingDto.getBreakStart());
        globalSettingsEntity.setBreakEnd(globalSettingDto.getBreakEnd());
        globalSettingsEntity.setReason(globalSettingDto.getReason());
        globalSettingsEntity.setSlotDuration(globalSettingDto.getSlotDuration());
        return globalSettingsEntity;
    }
    public GlobalSettingResponseDto toGlobalSettingResponseDto(GlobalSettingsEntity globalSettingsEntity){
        return GlobalSettingResponseDto.builder()
                .breakStart(globalSettingsEntity.getBreakStart())
                .breakEnd(globalSettingsEntity.getBreakEnd())
                .reason(globalSettingsEntity.getReason())
                .slotDuration(globalSettingsEntity.getSlotDuration())
                .build();
    }

}

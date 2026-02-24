package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.WorkingTimeResponseDto;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.WorkingTimeEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.WorkingTimeMapper;
import clinicManagement.repository.DoctorRepository;
import clinicManagement.repository.WorkingTimeRepository;
import clinicManagement.service.WorkingTimeService;
import clinicManagement.service.util.CustomTimeHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkingTimeServiceImpl implements WorkingTimeService {
    private final DoctorRepository doctorRepository;
    private final WorkingTimeMapper workingTimeMapper;
    private final WorkingTimeRepository workingTimeRepository;

    public WorkingTimeServiceImpl(DoctorRepository doctorRepository, WorkingTimeMapper workingTimeMapper, WorkingTimeRepository workingTimeRepository) {
        this.doctorRepository = doctorRepository;
        this.workingTimeMapper = workingTimeMapper;
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> addWorkingTime(WorkingTimeDto workingTimeDto, Long doctorId) {
        if (workingTimeDto.getWorkingDate().isBefore(LocalDate.now())) {
            throw new AppBadException("working date must be in the future");
        }
            DoctorEntity doctorEntity = doctorRepository.findById(doctorId).orElseThrow(() -> new DataNotFoundException("doctor not found"));
            if (workingTimeRepository.existsByDoctorEntity_IdAndWorkingDateAndIsExpired(doctorId, workingTimeDto.getWorkingDate(), true)) {
                throw new AppBadException("have a working time for this day");
            }
            workingTimeRepository.save(workingTimeMapper.toWorkingTimeEntity(workingTimeDto, doctorEntity));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("working time created", true, null, 201));
        }

    @Override
    public ResponseEntity<ApiResponse<?>> updateWorkingTime(WorkingTimeDto workingTimeDto, Long doctorId) {
       WorkingTimeEntity workingTime = workingTimeRepository.findByDoctorEntity_IdAndWorkingDateAndIsExpired(doctorId,workingTimeDto.getWorkingDate(),true).orElseThrow(()->new DataNotFoundException("not active working time for this day"));
       workingTime.setStartTime(workingTime.getStartTime());
       workingTime.setEndTime(workingTime.getEndTime());
       workingTimeRepository.save(workingTime);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(new ApiResponse<>("updated",true,null,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getPresentWeekWorkingTimeByDoctorId(Long doctorId) {
        if(!doctorRepository.existsById(doctorId)){
            throw new DataNotFoundException("doctor not found");
        }
        LocalDate startDate = CustomTimeHelper.thisWeekStart(LocalDate.now());
        List<WorkingTimeEntity> workingTimeEntityList = findAllByDateBetweenWithDoctorId(doctorId,startDate,startDate.plusDays(6));
        List<WorkingTimeResponseDto> responseDtoList = workingTimeEntityList.stream()
                .map(workingTimeMapper::toWorkingTimeResponseDto).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("this weeks workingTime",true,responseDtoList,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getLastWeekWorkingTimeByDoctorId(Long doctorId) {
        if(!doctorRepository.existsById(doctorId)){
            throw new DataNotFoundException("doctor not found");
        }        LocalDate endDate = CustomTimeHelper.thisWeekStart(LocalDate.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("this weeks workingTime",true,findAllByDateBetweenWithDoctorId(doctorId,endDate.minusDays(6),endDate),200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getNextWeekWorkingTimeByDoctorId(Long doctorId) {
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId).orElseThrow(()-> new DataNotFoundException("doctor not found"));
        LocalDate startDate = CustomTimeHelper.nextWeekStart(LocalDate.now());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("this weeks workingTime",true,findAllByDateBetweenWithDoctorId(doctorId,startDate,startDate.plusDays(6)),200));
    }


    public List<WorkingTimeEntity> findAllByDateBetweenWithDoctorId(Long doctorId,LocalDate startDate,LocalDate endDate){
        return workingTimeRepository.findAllByDoctorEntity_IdAndWorkingDateBetween(doctorId,startDate,endDate);
    }


}

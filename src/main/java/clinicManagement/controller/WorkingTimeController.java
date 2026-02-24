package clinicManagement.controller;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.WorkingTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/working-time")
@RequiredArgsConstructor
public class WorkingTimeController {
    private final WorkingTimeService workingTimeService;

    @GetMapping("/last-week-working-time/{id}")
    public ResponseEntity<ApiResponse<?>> getLastWeekWorkingTime(@PathVariable Long id){
        return workingTimeService.getLastWeekWorkingTimeByDoctorId(id);
    }

    @GetMapping("/present-week-working-time/{id}")
    public ResponseEntity<ApiResponse<?>> getPresentWeekWorkingTime(@PathVariable Long id){
        return workingTimeService.getPresentWeekWorkingTimeByDoctorId(id);
    }

    @GetMapping("/next-week-working-time/{id}")
    public ResponseEntity<ApiResponse<?>> getNextWeekWorkingTime(@PathVariable Long id){
        return workingTimeService.getNextWeekWorkingTimeByDoctorId(id);
    }

    @PostMapping("/add-working-time/{id}")
    public ResponseEntity<ApiResponse<?>> addWorkingTime(@RequestBody WorkingTimeDto workingTimeDto,@PathVariable Long id){
        return workingTimeService.addWorkingTime(workingTimeDto,id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody WorkingTimeDto workingTimeDto,@PathVariable Long id){
        return workingTimeService.updateWorkingTime(workingTimeDto,id);
    }

}

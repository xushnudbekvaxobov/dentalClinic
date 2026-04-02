package clinicManagement.controller;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT', 'DOCTOR')")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable UUID id){
        return userService.findById(id);
    }

//    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','PAIENT')")



}


package clinicManagement.exception;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.exception.dto.ExceptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        for(FieldError error : e.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        ExceptionResponseDto<Map<String,String>> exceptionResponseDto = new ExceptionResponseDto<>(errors,LocalDateTime.now());
        return ResponseEntity
                .status(400)
                .body(new ApiResponse<>(e.toString(),false,exceptionResponseDto,400));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleDataNotFoundException(DataNotFoundException e){
       ExceptionResponseDto<String> exceptionResponseDto = new ExceptionResponseDto<>(e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(404)
                .body(new ApiResponse<>(e.toString(),false,exceptionResponseDto,400));
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<ApiResponse<?>> handleNotAcceptableException(NotAcceptableException e){
        ExceptionResponseDto<String> exceptionResponseDto = new ExceptionResponseDto<>(e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(406)
                .body(new ApiResponse<>(e.toString(),false,exceptionResponseDto,400));
    }
    @ExceptionHandler(MailSendingException.class)
    public ResponseEntity<ApiResponse<?>> handleMailSendingException(MailSendingException e){
        ExceptionResponseDto<String> exceptionResponseDto = new ExceptionResponseDto<>(e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(500)
                .body(new ApiResponse<>(e.toString(),false,exceptionResponseDto,400));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception e) {
        ExceptionResponseDto<String> exceptionResponseDto = new ExceptionResponseDto<>(e.getMessage(), LocalDateTime.now());
        return ResponseEntity
                .status(500)
                .body(new ApiResponse<>(e.toString(), false, exceptionResponseDto, 400));
    }
}

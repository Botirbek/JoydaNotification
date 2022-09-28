package com.example.joydanotification.v1.exceptions;

import com.example.joydanotification.v1.dto.AppErrorDTO;
import com.example.joydanotification.v1.dto.DataDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle500(RuntimeException e, WebRequest webRequest) {
        return ResponseEntity.ok
                (new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle400(BadRequestException e, WebRequest webRequest) {
        return ResponseEntity.ok(
                new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.BAD_REQUEST, e.getMessage(), webRequest)));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle404(RuntimeException e, WebRequest webRequest) {
        return ResponseEntity.ok(
                new DataDTO<>(
                        new AppErrorDTO(
                                HttpStatus.NOT_FOUND, e.getMessage(), webRequest)));
    }


}

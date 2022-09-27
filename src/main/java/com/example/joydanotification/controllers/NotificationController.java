package com.example.joydanotification.controllers;

import com.example.joydanotification.dto.DataDTO;
import com.example.joydanotification.dto.NotificationItemDTO;
import com.example.joydanotification.enums.NotificationTypeEnum;
import com.example.joydanotification.services.FirebaseService;
import com.example.joydanotification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;
    private final FirebaseService firebaseService;

    @GetMapping("/getAll")
    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAll(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lan,
            @RequestParam  Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAll(lan,page,size);
    }

    @GetMapping("/getByType")
    public ResponseEntity<DataDTO<List<NotificationItemDTO>>>  getAllByType(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lan,
            @RequestParam NotificationTypeEnum type,
            @RequestParam Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAllByType(lan, type,page,size);
    }

    @GetMapping("/getAllByUserId")
    public ResponseEntity<DataDTO<List<NotificationItemDTO>>>  getAllByUserId(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lan,
            @RequestParam Long userId,
            @RequestParam Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAllByUserId(lan, userId,page,size);
    }

    @GetMapping("/getTypesByUserId")
    public ResponseEntity<DataDTO<List<String>>>  getAllByUserId(
            @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) String lan,
            @RequestParam Long userId)
    {
        return   notificationService.getTypesByUserId(lan, userId);
    }

    @GetMapping("/push")
    public ResponseEntity<DataDTO<String>>  getAllByUserId()
    {
        return  firebaseService.pushNotification();
    }
}

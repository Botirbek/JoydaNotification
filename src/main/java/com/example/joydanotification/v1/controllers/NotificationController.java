package com.example.joydanotification.v1.controllers;

import com.example.joydanotification.v1.dto.DataDTO;
import com.example.joydanotification.v1.dto.NotificationCreateDTO;
import com.example.joydanotification.v1.dto.NotificationItemDTO;
import com.example.joydanotification.v1.enums.NotificationTypeEnum;
import com.example.joydanotification.v1.services.FirebaseService;
import com.example.joydanotification.v1.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationController {

    private final NotificationService notificationService;
    private final FirebaseService firebaseService;


    @PostMapping("/create")
    public ResponseEntity<DataDTO<Long>> create (@RequestBody NotificationCreateDTO notificationCreateDTO){
         return notificationService.save(notificationCreateDTO);
    }

    @PostMapping("/changeReadStatus")
    public ResponseEntity<DataDTO<Boolean>> changeReadStatus(Long id, Boolean status){
        return notificationService.changeReadStatus(id,status);
    }

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
    
    @GetMapping("/getNewNotificationCount")
    public ResponseEntity<DataDTO<List<Integer>>> getCountNewNotification(){
        Long userId = 199368L;
        //TODO userId will found by jwt token
        return notificationService.getCountNewNotification(userId);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataDTO<NotificationItemDTO>>  getById(
            @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) String lan,
            @RequestParam Long id)
    {
        return   notificationService.getById(lan, id);
    }

    @GetMapping("/push")
    public ResponseEntity<DataDTO<String>>  getAllByUserId(){
        return  firebaseService.pushNotification();
    }
    
    
}

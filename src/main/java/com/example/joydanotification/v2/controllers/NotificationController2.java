package com.example.joydanotification.v2.controllers;

import com.example.joydanotification.config.JwtService;
import com.example.joydanotification.enums.NotificationTypeEnum;
import com.example.joydanotification.v2.services.FirebaseService2;
import com.example.joydanotification.v2.services.NotificationService2;
import com.example.joydanotification.v2.dto.DataDTO;
import com.example.joydanotification.v2.dto.NotificationCreateDTO;
import com.example.joydanotification.v2.dto.NotificationItemDTO;
import com.example.joydanotification.v2.dto.NotificationResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/data")
public class NotificationController2 {
    private final NotificationService2 notificationService;
    private final JwtService jwtService;
    private final FirebaseService2 firebaseService;

    @PostMapping("/create")
    public ResponseEntity<DataDTO<Long>> create (@RequestBody NotificationCreateDTO notificationCreateDTO){
         return notificationService.save(notificationCreateDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataDTO<NotificationResponseDTO>>  getAll(
            @RequestHeader("X-Mobile-Lang") String lan,
            @RequestParam  Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAll(lan,page,size);
    }

    @GetMapping("/getByType")
    public ResponseEntity<DataDTO<NotificationResponseDTO>>  getAllByType(
            @RequestHeader("X-Mobile-Lang") String lan,
            @RequestParam NotificationTypeEnum type,
            @RequestParam Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAllByType(lan, type,page,size);
    }

    @GetMapping("/getTypesByUserId")
    public ResponseEntity<DataDTO<List<String>>>  getAllByUserId(
            @RequestHeader("X-Mobile-Lang") String lan,
            @RequestParam Long userId)
    {
        return   notificationService.getTypesByUserId(lan, userId);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataDTO<NotificationItemDTO>>  getById(
            @RequestHeader("X-Mobile-Lang") String lan,
            @RequestParam Long id)
    {
        return   notificationService.getById(lan, id);
    }

    @GetMapping("/push")
    public ResponseEntity<DataDTO<String>>  getAllByUserId(){
        return  firebaseService.pushNotification();
    }

    @GetMapping("/notifications")
    public ResponseEntity<DataDTO<NotificationResponseDTO>>  getAllByUserId(
            @RequestHeader("X-Mobile-Lang") String lan,
            @RequestHeader("Authorization") String token,
            @RequestParam Integer page,
            @RequestParam(required = false) Integer size)
    {
        Long userId = jwtService.parseToken(token);
        return   notificationService.getAllByUserId(lan, userId,page,size);
    }

    @GetMapping("/newCount")
    public ResponseEntity<DataDTO<Integer>> getCountNewNotification(
            @RequestHeader("Authorization") String token
    ){
        Long userId = jwtService.parseToken(token);
        return notificationService.getCountNewNotification(userId);
    }

    @GetMapping("/read")
    public ResponseEntity<DataDTO<Boolean>> markAsReadById(
            Long id,
            @RequestHeader("Authorization") String token
    ){
        Long userId = jwtService.parseToken(token);
        return notificationService.read(id,userId);
    }

    @GetMapping("/read-all")
    public ResponseEntity<DataDTO<Boolean>> readAll(
            @RequestHeader("Authorization") String token
    ){
        Long userId = jwtService.parseToken(token);
        return notificationService.readAllByUserId(userId);
    }

}

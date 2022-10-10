package com.example.joydanotification.v1.controllers;

import com.example.joydanotification.config.JwtTokenFilter;
import com.example.joydanotification.entity.Notification;
import com.example.joydanotification.enums.NotificationTypeEnum;
import com.example.joydanotification.v1.dto.DataDTO;
import com.example.joydanotification.v1.dto.NotificationCreateDTO;
import com.example.joydanotification.v1.dto.NotificationItemDTO;
import com.example.joydanotification.v1.dto.NotificationResponseDTO;
import com.example.joydanotification.v1.services.FirebaseService;
import com.example.joydanotification.v1.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
public class NotificationController {
    private final NotificationService notificationService;
    private final JwtTokenFilter jwtTokenFilter;
    private final FirebaseService firebaseService;


    @PostMapping("/create")
    public ResponseEntity<DataDTO<Long>> create (@RequestBody NotificationCreateDTO notificationCreateDTO){
         return notificationService.save(notificationCreateDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataDTO<NotificationResponseDTO>>  getAll(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lan,
            @RequestParam  Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAll(lan,page,size);
    }

    @GetMapping("/getByType")
    public ResponseEntity<DataDTO<NotificationResponseDTO>>  getAllByType(
            @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lan,
            @RequestParam NotificationTypeEnum type,
            @RequestParam Integer page,
            @RequestParam(required = false) Integer size)
    {
        return   notificationService.getAllByType(lan, type,page,size);
    }

    @GetMapping("/getTypesByUserId")
    public ResponseEntity<DataDTO<List<String>>>  getAllByUserId(
            @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) String lan,
            @RequestParam Long userId)
    {
        log.info(new DataDTO<>("getAllByUserId"));
        return   notificationService.getTypesByUserId(lan, userId);
    }

    @GetMapping("/getById")
    public ResponseEntity<DataDTO<NotificationItemDTO>>  getById(
            @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = false) String lan,
            @RequestParam Long id)
    {
        log.error(new DataDTO<>("getById"));
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
        Long userId = 199368L;
        log.warn(new DataDTO<>("getCountNewNotification"));
        //TODO userId will found by jwt token
        jwtTokenFilter.parseAToken(token);
        return   notificationService.getAllByUserId(lan, userId,page,size);
    }

    @GetMapping("/notifications-new")
    public ResponseEntity<DataDTO<Integer>> getCountNewNotification(){
        Long userId = 199368L;
        log.warn(new DataDTO<>("getCountNewNotification"));
        //TODO userId will found by jwt token
        return notificationService.getCountNewNotification(userId);
    }

    @GetMapping("/read")
    public ResponseEntity<DataDTO<Boolean>> markAsReadById(Long id){
        Long userId = 199368L;
        //TODO userId will found by jwt token

        return notificationService.read(id,userId);
    }

    @GetMapping("/read-all")
    public ResponseEntity<DataDTO<Boolean>> readAll(){
        Long userId = 199368L;
        //TODO userId will found by jwt token

        return notificationService.readAllByUserId(userId);
    }

}

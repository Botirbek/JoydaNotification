package com.example.joydanotification.controllers;

import com.example.joydanotification.dto.DataDTO;
import com.example.joydanotification.dto.NotificationItemDTO;
import com.example.joydanotification.enums.NotificationTypeEnum;
import com.example.joydanotification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<DataDTO<List<NotificationItemDTO>>>  getAll(String lan, NotificationTypeEnum type){
        return   notificationService.getAll(lan, type);
    }

}

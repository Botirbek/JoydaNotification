package com.example.joydanotification.v2.services;

import com.example.joydanotification.v2.repository.PushNotificationRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushNotificationService2 {

    private final PushNotificationRepository2 pushNotificationRepository;


}

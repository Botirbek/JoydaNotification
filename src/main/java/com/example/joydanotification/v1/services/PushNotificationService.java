package com.example.joydanotification.v1.services;

import com.example.joydanotification.v1.repository.PushNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushNotificationService {

    private final PushNotificationRepository pushNotificationRepository;


}

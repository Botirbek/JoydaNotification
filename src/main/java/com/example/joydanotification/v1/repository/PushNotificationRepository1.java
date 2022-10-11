package com.example.joydanotification.v1.repository;

import com.example.joydanotification.entity.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushNotificationRepository1 extends JpaRepository<PushNotification, Long> {


}

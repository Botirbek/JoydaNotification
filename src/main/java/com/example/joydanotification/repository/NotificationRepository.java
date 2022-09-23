package com.example.joydanotification.repository;

import com.example.joydanotification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


    @Query("select n from Notification n where n.type = ?1 and n.status = ?2")
    List<Notification> findAllByTypeAndStatus(String type, String status);


    @Query("select n from Notification n where n.type <> 'payment'")
    List<Notification> findAllByTypeAndStatus();

}

package com.example.joydanotification.repository;

import com.example.joydanotification.entity.Notification;
import com.example.joydanotification.enums.NotificationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    /*
    For version 2 pagination by id
    @Query(nativeQuery = true, value = "select n.* from Notification n where n.status = 'active' and n.id >?1 limit ?2")
    List<Notification> findAll(Long lastId, Integer size);
    @Query(nativeQuery = true , value = "select n.* from Notification n where n.type = ?1 and n.user_id = ?2 and n.status = 'active' and n.id >?3 limit ?4")
    List<Notification> findAllByTypeAndUserId(NotificationTypeEnum type, Long userId,Long lastId, Integer size);

    @Query(nativeQuery = true ,value ="select n.* from Notification n where n.user_id = ?1 and n.status = 'active' and n.id >?2 limit ?3")
    List<Notification> findAllByUserId(Long userId,Long lastId, Integer size);

    @Query(nativeQuery = true , value ="select n.* from Notification n where n.type = ?1 and n.status = 'active' and n.id >?2 limit ?3")
    List<Notification> findAllByType(NotificationTypeEnum type, Long lastId, Integer size);

    */

    @Query(nativeQuery = true, value = "select n.* from Notification n where n.status = 'active' order by id offset ?2*(?1-1) limit ?2")
    List<Notification> findAll(Integer page, Integer size);
    @Query(nativeQuery = true , value = "select n.* from Notification n where n.type = ?1 and n.user_id = ?2 and n.status = 'active'  order by id offset ?4*(?3-1) limit ?4")
    List<Notification> findAllByTypeAndUserId(NotificationTypeEnum type, Long userId,Integer page, Integer size);

    @Query(nativeQuery = true ,value ="select n.* from Notification n where n.user_id = ?1 and n.status = 'active'  order by id offset ?3*(?2-1) limit ?3")
    List<Notification> findAllByUserId(Long userId,Integer page, Integer size);

    @Query(nativeQuery = true ,value ="select distinct n.type from Notification n where n.user_id = ?1 and n.status = 'active'")
    List<String> findTypesByUserId(Long userId);

    @Query(nativeQuery = true , value ="select n.* from Notification n where n.type = ?1 and n.status = 'active'  order by id offset ?3*(?2-1) limit ?3")
    List<Notification> findAllByType(NotificationTypeEnum type, Integer page, Integer size);

}

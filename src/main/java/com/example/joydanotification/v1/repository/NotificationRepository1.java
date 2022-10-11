package com.example.joydanotification.v1.repository;

import com.example.joydanotification.entity.Notification;
import com.example.joydanotification.enums.NotificationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotificationRepository1 extends JpaRepository<Notification, Long> {

    @Query(nativeQuery = true, value = "select n.* from Notification n where n.status = 'active' order by id offset ?2*(?1-1) limit ?2")
    List<Notification> findAll(Integer page, Integer size);
    @Query(nativeQuery = true , value = "select n.* from Notification n where n.type = ?1 and n.user_id = ?2 and n.status = 'active'  order by id offset ?4*(?3-1) limit ?4")
    List<Notification> findAllByTypeAndUserId(NotificationTypeEnum type, Long userId,Integer page, Integer size);

    @Query(nativeQuery = true ,value ="select n.* from Notification n where (n.user_id = ?1 or n.user_id = null) and n.status = 'active'  order by id offset ?3*(?2-1) limit ?3")
    List<Notification> findAllByUserId(Long userId,Integer page, Integer size);

    @Query(nativeQuery = true ,value ="select distinct n.type from Notification n where n.user_id = ?1 and n.status = 'active'")
    List<String> findTypesByUserId(Long userId);

    @Query(nativeQuery = true , value ="select n.* from Notification n where  CAST (n.type AS varchar) = ?1 and n.status = 'active'  order by id offset ?3*(?2-1) limit ?3")
    List<Notification> findAllByType(String type, Integer page, Integer size);

    @Query("select n from Notification n where n.read_status = false and n.user_id = ?1")
    List<Notification> countAllByReadStatusAndUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update Notification set read_status = true where user_id = ?1 and id = ?2")
    void markAsRead(Long userId, Long id);

    @Transactional
    @Modifying
    @Query("update Notification set read_status = true where user_id = ?1 and read_status = false")
    void markAsReadAll(Long userId);

}

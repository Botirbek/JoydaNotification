package com.example.joydanotification.v1.services;

import com.example.joydanotification.v1.dto.*;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.CreditRepaymentDto;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.OrderCardDto;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.PaymentDto;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.RedepositDto;
import com.example.joydanotification.v1.entity.Notification;
import com.example.joydanotification.v1.enums.NotificationStatusEnum;
import com.example.joydanotification.v1.enums.NotificationTypeEnum;
import com.example.joydanotification.v1.exceptions.CustomException;
import com.example.joydanotification.v1.repository.NotificationRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final Gson gson;
    private final NotificationRepository notificationRepository;

    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAll(String language, Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAll(page, size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        return ResponseEntity.ok(new DataDTO(dataList));
    }

    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAllByType(String language, NotificationTypeEnum type,Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAllByType(type.name(),page,size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        return ResponseEntity.ok(new DataDTO(dataList));
    }

    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAllByUserId(String language, Long userId, Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAllByUserId(userId,page,size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        return ResponseEntity.ok(new DataDTO(dataList));
    }

    public ResponseEntity<DataDTO<List<Integer>>> getCountNewNotification(Long userId) {
        int count = notificationRepository.findAllNewsByUserId(userId);
        return ResponseEntity.ok(new DataDTO(count));
    }

    public ResponseEntity<DataDTO<List<String>>> getTypesByUserId(String language, Long userId) {
        List<String> all = notificationRepository.findTypesByUserId(userId);
        return ResponseEntity.ok(new DataDTO(all));
    }

    public ResponseEntity<DataDTO<NotificationItemDTO>> getById(String language, Long id) {
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()){
            throw new CustomException("Couldn't found notification by id = "+id);
        }
        return ResponseEntity.ok(new DataDTO(parseData(byId.get(),language)));
    }

    public ResponseEntity<DataDTO<Boolean>> changeReadStatus(Long id, Boolean readStatus){
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()){
            throw new CustomException("Couldn't found notification by id = "+id);
        }

        Notification notification = byId.get();
        notification.setRead_status(readStatus);
        notificationRepository.save(notification);
        return ResponseEntity.ok(new DataDTO<>(true));
    }

    public ResponseEntity<DataDTO<Long>> save( NotificationCreateDTO notificationCreateDTO) {
        //TODO userId will found by jwt token
        Long userId = 199368L;

        Notification build = Notification.builder()
                .data(gson.toJson(null))
                .status(NotificationStatusEnum.active)
                .image(notificationCreateDTO.getImage())
                .text(gson.toJson(notificationCreateDTO.getText()))
                .title(gson.toJson(notificationCreateDTO.getTitle()))
                .type(notificationCreateDTO.getType())
                .user_id(userId)
                .build();

        Notification save = notificationRepository.save(build);

        return ResponseEntity.ok(new DataDTO<>(save.getId()));
    }

    private NotificationItemDTO parseData(Notification notification, String language){
        switch (language) {
            case "en":
                return NotificationItemDTO.builder()
                        .id(notification.getId().toString())
                        .title(gson.fromJson(notification.getTitle(), LanguageDTO.class).getEn())
                        .date(notification.getDate().toString())
                        .image(notification.getImage())
                        .text(gson.fromJson(notification.getText(), LanguageDTO.class).getEn())
                        .type(notification.getType().name())
                        .data(getDataByType(notification))
                        .build();
            case "ru":
                return NotificationItemDTO.builder()
                        .id(notification.getId().toString())
                        .title(gson.fromJson(notification.getTitle(), LanguageDTO.class).getRu())
                        .date(notification.getDate().toString())
                        .image(notification.getImage())
                        .text(gson.fromJson(notification.getText(), LanguageDTO.class).getRu())
                        .type(notification.getType().name())
                        .data(getDataByType(notification))
                        .build();
            case "uz":
                return NotificationItemDTO.builder()
                        .id(notification.getId().toString())
                        .title(gson.fromJson(notification.getTitle(), LanguageDTO.class).getUz())
                        .date(notification.getDate().toString())
                        .image(notification.getImage())
                        .text(gson.fromJson(notification.getText(), LanguageDTO.class).getUz())
                        .type(notification.getType().name())
                        .data(getDataByType(notification))
                        .build();
        }
        return null;
    }

    private List<NotificationItemDTO> getDataList(List<Notification> all, String language){
        List<NotificationItemDTO> list = new ArrayList<>();

        all.stream().forEach(notification -> {
            list.add(parseData(notification, language));
        });

        return list;
    }

    private List<BasicDTO> getDataByType(Notification notification) {
        if (notification.getData() == null||notification.getData().equals("[]")) return new ArrayList<>();

        if (notification.getData().startsWith("[") && (notification.getData().endsWith("]"))) {
            switch (notification.getType()) {
                case redeposit -> {
                    return gson.fromJson(notification.getData(), new TypeToken<List<RedepositDto>>() {
                    }.getType());
                }
                case order_card -> {
                    return gson.fromJson(notification.getData(), new TypeToken<List<OrderCardDto>>() {
                    }.getType());
                }
                case payment -> {
                    return gson.fromJson(notification.getData(), new TypeToken<List<PaymentDto>>() {
                    }.getType());
                }
                case credit_repayment -> {
                    return gson.fromJson(notification.getData(), new TypeToken<List<CreditRepaymentDto>>() {
                    }.getType());
                }
            }
        }else {
            List<BasicDTO> list = new ArrayList<>();
            switch (notification.getType()) {
                case redeposit -> {
                    list.add(gson.fromJson(notification.getData(), RedepositDto.class));
                    break;
                }
                case order_card -> {
                    list.add(gson.fromJson(notification.getData(), OrderCardDto.class));
                    break;
                }
                case payment -> {
                    list.add(gson.fromJson(notification.getData(), PaymentDto.class));
                    break;
                }
                case credit_repayment -> {
                    list.add(gson.fromJson(notification.getData(), CreditRepaymentDto.class));
                    break;
                }
            }
            return list;
        }
        return null;
    }


}

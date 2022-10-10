package com.example.joydanotification.v1.services;

import com.example.joydanotification.v1.dto.*;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.CreditRepaymentDto;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.OrderCardDto;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.PaymentDto;
import com.example.joydanotification.v1.dto.notificationTypeDTOS.RedepositDto;
import com.example.joydanotification.entity.Notification;
import com.example.joydanotification.enums.NotificationStatusEnum;
import com.example.joydanotification.enums.NotificationTypeEnum;
import com.example.joydanotification.exceptions.CustomException;
import com.example.joydanotification.v1.repository.NotificationRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificationService {
    private final Gson gson;
    private final NotificationRepository notificationRepository;

    public ResponseEntity<DataDTO<NotificationResponseDTO>>  getAll(String language, Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAll(page, size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        int pageCount =(int) Math.ceil((double) dataList.size()/size);
        return ResponseEntity.ok(new DataDTO(new NotificationResponseDTO(dataList,pageCount)));
    }

    public ResponseEntity<DataDTO<NotificationResponseDTO>> getAllByType(String language, NotificationTypeEnum type,Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAllByType(type.name(),page,size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        int pageCount =(int) Math.ceil((double) dataList.size()/size);
        return ResponseEntity.ok(new DataDTO(new NotificationResponseDTO(dataList,pageCount)));
    }

    public ResponseEntity<DataDTO<NotificationResponseDTO>> getAllByUserId(String language, Long userId, Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAllByUserId(userId,page,size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        int pageCount =(int) Math.ceil((double) dataList.size()/size);
        return ResponseEntity.ok(new DataDTO(new NotificationResponseDTO(dataList,pageCount)));
    }

    public ResponseEntity<DataDTO<List<String>>> getTypesByUserId(String language, Long userId) {
        List<String> all = notificationRepository.findTypesByUserId(userId);
        return ResponseEntity.ok(new DataDTO(all));
    }

    public ResponseEntity<DataDTO<NotificationItemDTO>> getById(String language, Long id) {
        log.warn("awdaw");
        Optional<Notification> byId = notificationRepository.findById(id);
        if (byId.isEmpty()){
            throw new CustomException("Couldn't found notification by id = "+id);
        }
        return ResponseEntity.ok(new DataDTO(parseData(byId.get(),language)));
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

    public ResponseEntity<DataDTO<Integer>> getCountNewNotification(Long userId) {
        int count = notificationRepository.countAllByReadStatusAndUserId(userId);
        return ResponseEntity.ok(new DataDTO(count));
    }

    public ResponseEntity<DataDTO<Boolean>> read(Long id, Long userId){
        notificationRepository.markAsRead(userId, id);

        return ResponseEntity.ok(new DataDTO<>(true));
    }

    public ResponseEntity<DataDTO<Boolean>> readAllByUserId(Long userId) {
        try {
            notificationRepository.markAsReadAll(userId);
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
        return ResponseEntity.ok(new DataDTO<>(true));
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

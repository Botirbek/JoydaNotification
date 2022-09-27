package com.example.joydanotification.services;

import com.example.joydanotification.dto.BasicDTO;
import com.example.joydanotification.dto.DataDTO;
import com.example.joydanotification.dto.LanguageDTO;
import com.example.joydanotification.dto.NotificationItemDTO;
import com.example.joydanotification.dto.notificationTypeDTOS.CreditRepaymentDto;
import com.example.joydanotification.dto.notificationTypeDTOS.OrderCardDto;
import com.example.joydanotification.dto.notificationTypeDTOS.PaymentDto;
import com.example.joydanotification.dto.notificationTypeDTOS.RedepositDto;
import com.example.joydanotification.entity.Notification;
import com.example.joydanotification.enums.NotificationTypeEnum;
import com.example.joydanotification.repository.NotificationRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final Gson gson;
    private final NotificationRepository notificationRepository;

    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAll(String language,Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAll(page, size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        return ResponseEntity.ok(new DataDTO(dataList));
    }

    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAllByType(String language, NotificationTypeEnum type,Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAllByType(type,page,size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        return ResponseEntity.ok(new DataDTO(dataList));
    }

    public ResponseEntity<DataDTO<List<NotificationItemDTO>>> getAllByUserId(String language, Long userId, Integer page, Integer size) {
        if (size==null) size = 30;
        List<Notification> all = notificationRepository.findAllByUserId(userId,page,size);
        List<NotificationItemDTO> dataList = getDataList(all, language);
        return ResponseEntity.ok(new DataDTO(dataList));
    }

    public ResponseEntity<DataDTO<List<String>>> getTypesByUserId(String language, Long userId) {
        List<String> all = notificationRepository.findTypesByUserId(userId);
        return ResponseEntity.ok(new DataDTO(all));
    }

    private List<NotificationItemDTO> getDataList(List<Notification> all, String language){
        List<NotificationItemDTO> list = new ArrayList<>();

        all.stream().forEach(notification -> {
            switch (language) {
                case "en":
                    list.add(NotificationItemDTO.builder()
                            .id(notification.getId().toString())
                            .title(gson.fromJson(notification.getTitle(), LanguageDTO.class).getEn())
                            .date(notification.getDate().toString())
                            .image(notification.getImage())
                            .text(gson.fromJson(notification.getText(), LanguageDTO.class).getEn())
                            .type(notification.getType().name())
                            .data(getDataByType(notification))
                            .build());
                    break;
                case "ru":
                    list.add(NotificationItemDTO.builder()
                            .id(notification.getId().toString())
                            .title(gson.fromJson(notification.getTitle(), LanguageDTO.class).getRu())
                            .date(notification.getDate().toString())
                            .image(notification.getImage())
                            .text(gson.fromJson(notification.getText(), LanguageDTO.class).getRu())
                            .type(notification.getType().name())
                            .data(getDataByType(notification))
                            .build());
                    break;
                case "uz":
                    list.add(NotificationItemDTO.builder()
                            .id(notification.getId().toString())
                            .title(gson.fromJson(notification.getTitle(), LanguageDTO.class).getUz())
                            .date(notification.getDate().toString())
                            .image(notification.getImage())
                            .text(gson.fromJson(notification.getText(), LanguageDTO.class).getUz())
                            .type(notification.getType().name())
                            .data(getDataByType(notification))
                            .build());
                    break;
            }
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

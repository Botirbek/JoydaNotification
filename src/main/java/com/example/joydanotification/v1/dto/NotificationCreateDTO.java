package com.example.joydanotification.v1.dto;

import com.example.joydanotification.enums.NotificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationCreateDTO {
    private LanguageDTO title;
    private String image;
    private NotificationTypeEnum type;
    private LanguageDTO text;
}

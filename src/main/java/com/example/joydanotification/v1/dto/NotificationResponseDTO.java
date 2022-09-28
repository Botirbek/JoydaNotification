package com.example.joydanotification.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponseDTO {

    private List<NotificationItemDTO> notifications;
    private int pageCount;
}

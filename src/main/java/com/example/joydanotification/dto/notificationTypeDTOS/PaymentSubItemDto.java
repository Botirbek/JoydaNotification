package com.example.joydanotification.dto.notificationTypeDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSubItemDto {
    private Integer id;
    private String title;
    private String logo;
    private Double min;
    private Double max;
    private Integer order;
    private String provider;
    private Integer category_id;
    private String  category_logo;
    private String category_title;
    private List<ServiceItemDto> list;
}

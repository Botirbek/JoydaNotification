package com.example.joydanotification.v2.dto.notificationTypeDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSubFieldDataItemOldDto {
    private String key;
    private String val;
}

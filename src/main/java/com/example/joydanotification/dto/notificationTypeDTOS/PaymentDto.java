package com.example.joydanotification.dto.notificationTypeDTOS;

import com.example.joydanotification.dto.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto extends BasicDTO {
    private String data;
}

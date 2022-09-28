package com.example.joydanotification.v1.dto.notificationTypeDTOS;

import com.example.joydanotification.v1.dto.BasicDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditRepaymentDto extends BasicDTO {
    private String amount;
    private Long loan_id;
}

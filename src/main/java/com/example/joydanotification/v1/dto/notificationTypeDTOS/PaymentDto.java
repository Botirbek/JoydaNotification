package com.example.joydanotification.v1.dto.notificationTypeDTOS;

import com.example.joydanotification.v1.dto.BasicDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
public class PaymentDto extends BasicDTO {
    private String mode;
    private String amount;
    private DetailDataDto details;
    private List<IdValueDto> payload;
    private PaymentSubItemDto supplier;
    private LoadDataDto load_data;
    private String operation_id;

}

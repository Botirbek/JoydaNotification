package com.example.joydanotification.v1.dto.notificationTypeDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceItemDto {
  private Integer id;
  private String title;
  private List<PaymentSubFieldItemDto> fields;
}

package com.example.joydanotification.v1.dto.notificationTypeDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdValueDto {
    private String id;
    private String value;
}

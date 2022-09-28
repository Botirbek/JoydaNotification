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
public class DetailDataDto {
    private List<KeyValueDto> additional;
    private String category_id;
    private String comment;
}

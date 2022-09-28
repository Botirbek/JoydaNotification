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
public class RedepositDto extends BasicDTO {

    private Long dep_id;
}

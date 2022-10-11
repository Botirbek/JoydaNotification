package com.example.joydanotification.v2.dto.notificationTypeDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSubFieldItemDto {
    private Integer id;
    private String title;
    private String format;
    private String type;
    private String element;
    private String regex;
    private String hint;
    private Boolean check_amount;
    private List<PaymentSubFieldDataItemOldDto> data;
    private List<PaymentSubFieldDataItemNewDto> nested_data;
    private Boolean optional;
    private Boolean is_only_read;
    private Boolean can_be_used_in_search;
    private Integer field_id;
}

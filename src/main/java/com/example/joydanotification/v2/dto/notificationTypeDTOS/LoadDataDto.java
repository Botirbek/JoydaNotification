package com.example.joydanotification.v2.dto.notificationTypeDTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadDataDto {
    private String inn;
    private String mfo;
    private String amount;
    private String account;
    private String card_id;
    private String doc_date;
    private Boolean is_budget;
    private String doc_number;
    private String  doc_subject;
    private String  account_name;
    private String receiver;
}

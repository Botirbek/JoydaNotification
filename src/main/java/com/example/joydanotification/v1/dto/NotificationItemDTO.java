package com.example.joydanotification.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationItemDTO {
    private String id;
    private String title;
    private String date;
    private String image;
    private String text;
    private String type;
    private List<BasicDTO> data;

}

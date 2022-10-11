package com.example.joydanotification.v2.dto.firebase;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RequestDto {
   private String to;
   private int time_to_live;
   private NotificationF notification;
   private DataF data;

}

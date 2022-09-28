package com.example.joydanotification.v1.services;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FirebaseResponse {
   private Number multicast_id;
   private Number success;
   private Number failure;
   private FirebaseResponseResult result;

}

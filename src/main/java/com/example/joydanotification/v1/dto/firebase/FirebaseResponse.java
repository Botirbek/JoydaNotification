package com.example.joydanotification.v1.dto.firebase;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FirebaseResponse {
   private Number multicast_id;
   private Integer success;
   private Integer failure;
   private FirebaseResponseResult result;

}

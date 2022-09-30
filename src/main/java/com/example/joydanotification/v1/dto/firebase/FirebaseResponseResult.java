package com.example.joydanotification.v1.dto.firebase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties
public class FirebaseResponseResult {
    private Integer id;
    private String registration_id;
    private String error;
}

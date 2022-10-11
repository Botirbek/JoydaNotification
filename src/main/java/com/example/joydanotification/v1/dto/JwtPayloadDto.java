package com.example.joydanotification.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtPayloadDto {
    private String exp;
    private Long id;
    private String type;
    private String uid;

}

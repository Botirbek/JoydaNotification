package com.example.joydanotification.config;

import com.example.joydanotification.v1.dto.JwtPayloadDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
@RequiredArgsConstructor
public class JwtService  {

    private final Gson gson;

    public Long  parseToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        token = token.replace("Bearer", "");
        String[] split = token.split("\\.");
        String payload = new String(decoder.decode(split[1]));
        JwtPayloadDto jwtPayloadDto = gson.fromJson(payload, JwtPayloadDto.class);
        return jwtPayloadDto.getId();
    }


}

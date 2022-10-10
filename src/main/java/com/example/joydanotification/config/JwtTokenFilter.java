package com.example.joydanotification.config;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class JwtTokenFilter extends OncePerRequestFilter {

    private final Gson gson;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    public String  parseAToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        token = token.replace("Bearer", "");
        String[] split = token.split("\\.");
        String payload = new String(decoder.decode(split[1]));
        String json = gson.toJson(payload);
        System.out.println(json);
        return json;
    }
}

package com.example.joydanotification.config;

import com.example.joydanotification.JoydaNotificationApplication;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {

    @Bean
    public Gson gson(){
        return new Gson();
    }

}

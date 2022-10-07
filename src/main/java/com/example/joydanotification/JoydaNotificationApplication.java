package com.example.joydanotification;

import com.example.joydanotification.v1.dto.DataDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition
public class JoydaNotificationApplication {

    private static final Logger logger = LogManager.getLogger(JoydaNotificationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JoydaNotificationApplication.class, args);

        logger.warn(new DataDTO<>("Hello"));
    }

}

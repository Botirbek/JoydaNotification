package com.example.joydanotification.v1.services;

import com.example.joydanotification.v1.dto.DataDTO;
import com.example.joydanotification.v1.dto.firebase.DataF;
import com.example.joydanotification.v1.dto.firebase.FirebaseResponse;
import com.example.joydanotification.v1.dto.firebase.NotificationF;
import com.example.joydanotification.v1.dto.firebase.RequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Service
@Log4j2
public class FirebaseService1 {
    private static final String TOKEN = "eEil5QobRkmzr9A9AemZnA:APA91bHRs_3LyYEFijVZHR68TBSxU2YmheE7yOpFsQoWmF1oSsd2jxIjgFypY0u8oSwD10OxvUAUqQoPaNRSv586I1DuGgaTAHlB1IKFAk4FwQvUQGnxi_yjO5ppMneL564ixqInmiF0";
    private static final String KEY = "AAAAMrs8Or4:APA91bF_sFBviC9WfxrgP1x3qk0rjHLwfY1DqGKxd9yen-1jy8Oiwy29HPYt1vxiouhAqpVBPBaQrP1YY5NtfhIX7ffrdXSudfIj4KiY8YoGcbI6bGtEWqqWa0jZUA0nmtNbSTYDFO8_";
    private String PROXY_SERVER_HOST = "172.25.42.77";
    private final int PROXY_SERVER_PORT = 808;
    private int TIME = 900;
    private int tryCount = 5;
    private String BASE_URL = "https://fcm.googleapis.com/fcm/send";

    public ResponseEntity<DataDTO<String>> pushNotification() {
        Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(PROXY_SERVER_HOST, PROXY_SERVER_PORT));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(KEY);

        HttpEntity<RequestDto> request = new HttpEntity<>(
                RequestDto.builder()
                        .to(TOKEN)
                        .time_to_live(TIME)
                        .data(
                                DataF.builder()
                                        .id(1)
                                        .click_action("Send sms")
                                        .build())
                        .notification(
                                NotificationF.builder()
                                        .body("Test body")
                                        .title("Test title")
                                        .build()
                        )
                        .build(), headers
        );

        FirebaseResponse responseDto = restTemplate.postForObject(BASE_URL, request, FirebaseResponse.class);

        return ResponseEntity.ok(new DataDTO<>(responseDto.toString()));
    }

    public ResponseEntity<DataDTO<String>> pushNotificationFlux() {
        return null;
    }
}

package com.example.joydanotification.services;

import com.example.joydanotification.dto.DataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.InetSocketAddress;
import java.net.Proxy;

@Service
public class FirebaseService {
    private String PROXY_SERVER_HOST = "172.25.42.77";
    private static final int PROXY_SERVER_PORT = 808 ;

    public ResponseEntity<DataDTO<String>> pushNotification(){
        Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(PROXY_SERVER_HOST, PROXY_SERVER_PORT));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        String s = restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", "", String.class);

        return ResponseEntity.ok(new DataDTO<>(s));

    }

}

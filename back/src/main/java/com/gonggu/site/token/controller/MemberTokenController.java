package com.gonggu.site.token.controller;

import com.gonggu.site.firestore.FireStore;
import com.gonggu.site.token.service.MemberTokenService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/token")
public class MemberTokenController {

    @Autowired
    MemberTokenService memberTokenService;

    @Autowired
    FireStore fireStore;

    @GetMapping("/{token}")
    public void test(@PathVariable String token) {
        sendMessage(token, "안녕");
    }

    public void sendAlert(String token) {
        Message message = Message.builder()
                .putData("title", "09")
                .putData("desc", "등록하신 공고모집이 마감되었습니다.")
                .setToken(token)
                .build();
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message : " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("Failed sent message : " + e.getMessage());
        }
    }

    public void sendMessage(String token, String msg) {
        try {
            System.out.println("token : " + token);
            GoogleCredential googleCredential = fireStore.getCredentials();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("content-type", MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.add("Authorization", "Bearer " + googleCredential.getAccessToken());

            JSONObject notification = new JSONObject();
            notification.put("body", msg);
            notification.put("title", "알림");

            JSONObject message = new JSONObject();
            message.put("token", token);
            message.put("notification", notification);

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("message", message);

            HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonParams, httpHeaders);
            RestTemplate rt = new RestTemplate();

            ResponseEntity<String> res = rt.exchange("https://fcm.googleapis.com/v1/projects/imagestore-39fb6/messages:send"
                                                    , HttpMethod.POST
                                                    , httpEntity
                                                    , String.class);
            if(res.getStatusCode() != HttpStatus.OK) {
                System.out.println("Push Failed");
            }else {
                System.out.println("Push Success");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

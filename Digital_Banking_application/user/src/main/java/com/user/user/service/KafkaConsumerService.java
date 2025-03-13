package com.user.user.service;


import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@EnableKafka
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user", groupId = "banking-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}

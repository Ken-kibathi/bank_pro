package com.accounts.accounts.services;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "accounts", groupId = "banking-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
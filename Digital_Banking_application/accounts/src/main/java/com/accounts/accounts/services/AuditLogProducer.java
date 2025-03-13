package com.accounts.accounts.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditLogProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendAuditLog(String message) {
        log.info("Sending Audit Log: {}", message);
        kafkaTemplate.send("audit-events", message);
    }
}
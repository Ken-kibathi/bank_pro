package com.example.AuditLoggingService.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuditLogProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AuditLogProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAuditLog(String message) {
        kafkaTemplate.send("audit-log", message);
    }
}

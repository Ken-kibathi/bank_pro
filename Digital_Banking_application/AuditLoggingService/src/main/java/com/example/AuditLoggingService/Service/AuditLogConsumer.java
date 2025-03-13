package com.example.AuditLoggingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditLogConsumer {

    @Autowired
    private AuditService auditService;
    @KafkaListener(topics = "audit-log", groupId = "AuditLoggingService")
    public void listen(String message) {
        System.out.println("Received Audit Log: " + message);
        // TODO: Save to MongoDB

        auditService.logAction("Kafka Event", message);

        System.out.println("✅ logAction() method called.");
    }
}

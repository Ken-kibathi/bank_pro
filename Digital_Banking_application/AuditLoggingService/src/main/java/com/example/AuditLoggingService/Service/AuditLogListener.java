/*package com.example.AuditLoggingService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditLogListener {
    @Autowired
    private AuditService auditLoggingService;

    @KafkaListener(topics = "audit-log", groupId = "audit-group")
    public void listen(String message) {
        System.out.println("🟢 Received Audit Log from Kafka: " + message);

        // Try saving to MongoDB
        auditLoggingService.saveLog("Kafka Event", message);

        System.out.println("✅ logAction() method was called.");
    }
}*/

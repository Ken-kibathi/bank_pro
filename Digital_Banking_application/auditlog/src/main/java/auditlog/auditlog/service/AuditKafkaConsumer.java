package auditlog.auditlog.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuditKafkaConsumer {

    private final AuditService auditService;

    public AuditKafkaConsumer(AuditService auditService) {
        this.auditService = auditService;
    }

    @KafkaListener(topics = "audit-logs", groupId = "audit-service")
    public void consume(ConsumerRecord<String, String> record) {
        // Convert JSON to AuditLog object and save
        System.out.println("Received audit log: " + record.value());
    }
}


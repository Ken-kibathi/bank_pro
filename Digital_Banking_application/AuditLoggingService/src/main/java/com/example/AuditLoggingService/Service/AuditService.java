package com.example.AuditLoggingService.Service;

import com.example.AuditLoggingService.Entity.AuditLog;
import com.example.AuditLoggingService.Repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    @Autowired
    private AuditRepository auditRepository;

    public AuditLog saveLog(AuditLog log) {
        return auditRepository.save(log);
    }

    // Retrieve all logs
    public List<AuditLog> getAllLogs() {
        return auditRepository.findAll();
    }

    // Retrieve logs filtered by event type
    public List<AuditLog> getLogsByEventType(String eventType) {
        return auditRepository.findByEventType(eventType);
    }

    // Retrieve logs filtered by user
    public List<AuditLog> getLogsByUser(String user) {
        return auditRepository.findByUser(user);
    }

    /*public void logAction(String event, String details) {
        AuditLog log = new AuditLog(event, details);

        System.out.println("🟡 Attempting to save: " + log);

        AuditLog savedLog = auditRepository.save(log);

        System.out.println("✅ Saved Audit Log ID: " + savedLog.getId());
    }*/

    public void logAction(String event, String details) {
        AuditLog log = new AuditLog(event, details);

        System.out.println("🟡 Attempting to save: " + log);

        try {
            AuditLog savedLog = auditRepository.save(log);
            System.out.println("✅ Successfully saved: " + savedLog);
        } catch (Exception e) {
            System.err.println("❌ Failed to save log: " + e.getMessage());
        }
    }
}

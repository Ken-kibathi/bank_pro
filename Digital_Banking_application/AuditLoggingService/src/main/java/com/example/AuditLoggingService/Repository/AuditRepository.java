package com.example.AuditLoggingService.Repository;

import com.example.AuditLoggingService.Entity.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository extends MongoRepository<AuditLog, String> {
    List<AuditLog> findByEventType(String eventType);
    List<AuditLog> findByUser(String user);
}

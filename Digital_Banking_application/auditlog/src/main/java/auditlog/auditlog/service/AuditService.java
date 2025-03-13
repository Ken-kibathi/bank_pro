package auditlog.auditlog.service;

import auditlog.auditlog.model.AuditLog;
import auditlog.auditlog.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;

@Service
public class AuditService {

    private final AuditLogRepository repository;

    public AuditService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public void logEvent(String eventType, String action, String userId, String accountId, Double amount, Map<String, Object> metadata) {
        AuditLog log = new AuditLog();
        log.setEventType(eventType);
        log.setAction(action);
        log.setUserId(userId);
        log.setAccountId(accountId);
        log.setAmount(amount);
        log.setTimestamp(Instant.now());
        log.setMetadata(metadata);
        repository.save(log);
    }
}


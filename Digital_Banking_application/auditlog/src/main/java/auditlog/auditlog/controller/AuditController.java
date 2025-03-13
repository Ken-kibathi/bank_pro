package auditlog.auditlog.controller;
import auditlog.auditlog.model.AuditLog;
import auditlog.auditlog.repository.AuditLogRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditLogRepository repository;

    public AuditController(AuditLogRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user/{userId}")
    public List<AuditLog> getLogsByUser(@PathVariable String userId) {
        return repository.findByUserId(userId);
    }

    @GetMapping("/account/{accountId}")
    public List<AuditLog> getLogsByAccount(@PathVariable String accountId) {
        return repository.findByAccountId(accountId);
    }
}

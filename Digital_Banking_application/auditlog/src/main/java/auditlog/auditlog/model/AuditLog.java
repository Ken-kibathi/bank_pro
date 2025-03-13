package auditlog.auditlog.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.Map;
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "audit_logs")
    public class AuditLog {

        @Id
        private String id;
        private String eventType;
        private String action;
        private String userId;
        private String accountId;
        private Double amount;
        private Instant timestamp;
        private Map<String, Object> metadata;

    }



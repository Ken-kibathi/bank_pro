package com.example.AuditLoggingService.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@Document(collection = "auditLogs")
public class AuditLog {
    @Id
    private String id; // MongoDB auto-generates an ID
    private String event;
    private String details;  // If you want to store extra details
    private Instant timestamp;

    public AuditLog(String event, String details, Instant timestamp) {
        this.event = event;
        this.details = details;
        this.timestamp = timestamp;
    }


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public AuditLog(String event, String details) {
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "event='" + event + '\'' +
                ", details='" + details + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

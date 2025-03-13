package com.example.AuditLoggingService.Controller;


import com.example.AuditLoggingService.Entity.AuditLog;
import com.example.AuditLoggingService.Service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {
    private AuditService auditService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/logs")
    public List<AuditLog> getAllLogs() {
        return auditService.getAllLogs();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/logs/event")
    public List<AuditLog> getLogsByEventType(@RequestParam String eventType) {
        return auditService.getLogsByEventType(eventType);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/logs/user")
    public List<AuditLog> getLogsByUser(@RequestParam String user) {
        return auditService.getLogsByUser(user);
    }
}

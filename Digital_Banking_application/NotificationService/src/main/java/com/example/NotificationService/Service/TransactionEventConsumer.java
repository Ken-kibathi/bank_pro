package com.example.NotificationService.Service;

import com.example.NotificationService.DTO.TransactionKafkaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventConsumer {

    private EmailService emailService;

    @Autowired
    public TransactionEventConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "transaction-events", groupId = "notification-service")
    public void consumeTransactionEvent(TransactionKafkaDto event) {
        if (event == null) {
            System.out.println("Received null transaction event");
            return;
        }

        String emailBody = "Transaction Alert: \n" +
                "Type: " + event.getTransactionType() + "\n" +
                "Amount: " + event.getAmount() + "\n" +
                "Date: " + event.getTransactionDate();

        emailService.sendEmail("kennkibathi@gmail.com", "Transaction Notification", emailBody);
    }
}

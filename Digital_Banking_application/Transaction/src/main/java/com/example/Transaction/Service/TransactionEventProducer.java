package com.example.Transaction.Service;

import com.example.Transaction.DTO.TransactionKafkaDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventProducer {

    private final KafkaTemplate<String, TransactionKafkaDto> kafkaTemplate;
    private static final String TOPIC = "transaction-events";

    public TransactionEventProducer(KafkaTemplate<String, TransactionKafkaDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransactionEvent(TransactionKafkaDto event) {
        kafkaTemplate.send(TOPIC, event);
    }
}

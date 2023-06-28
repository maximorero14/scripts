package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sqs")
public class TransactionLogApprovedConsumer {

    private final QueueService queueService;

    @Value("${aws.sqs.transactionlog-approved.queueName}")
    private String transactionlogApprovedQueueName;

    @Autowired
    public TransactionLogApprovedConsumer(QueueService queueService) {
        this.queueService = queueService;
    }
    @Scheduled(fixedRateString = "${aws.sqs.transactionlog-approved.scheduled_time}")
    @PostMapping("/recive-message")
    public void startConsumer() {
        List<CustomQueueMessage> messages = queueService.receiveMessage(transactionlogApprovedQueueName);
        for (CustomQueueMessage message : messages) {
            queueService.deleteMessage(message);
        }
    }

}

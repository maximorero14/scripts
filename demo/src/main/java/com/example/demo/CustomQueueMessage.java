package com.example.demo;

import lombok.Data;

@Data
public class CustomQueueMessage {
    String messageId;
    String body;

    String receiptHandle;

    String queueUrl;
    boolean delete;
}

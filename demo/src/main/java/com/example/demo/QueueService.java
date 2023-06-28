package com.example.demo;

import java.util.List;

public interface QueueService {
    List<CustomQueueMessage> receiveMessage(String queueUrl);
    void deleteMessage(CustomQueueMessage customQueueMessage);
}
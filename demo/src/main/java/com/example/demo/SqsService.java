package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SqsService implements QueueService{

    private final SqsClient sqsClient;

    @Autowired
    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }


    public List<CustomQueueMessage> receiveMessage(String queueUrl) {

        log.info("[name: start_to_process_sqs]");

        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(10)  // Número máximo de mensajes a recibir
                .build();

        ReceiveMessageResponse response = sqsClient.receiveMessage(request);

        return convertToMessageSqs(response.messages(), queueUrl);
    }

    public void deleteMessage(CustomQueueMessage customQueueMessage) {

        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(customQueueMessage.getQueueUrl())
                .receiptHandle(customQueueMessage.getReceiptHandle())
                .build();
        log.info("delete message sqs " + customQueueMessage.getMessageId(), " " + customQueueMessage.getBody());

        sqsClient.deleteMessage(deleteRequest);
    }

    public List<CustomQueueMessage> convertToMessageSqs(List<Message> messages, String queueUrl) {
        List<CustomQueueMessage> messageSqsList = new ArrayList<>();

        for (Message message : messages) {
            CustomQueueMessage messageSqs = new CustomQueueMessage();
            messageSqs.setMessageId(message.messageId());
            messageSqs.setBody(message.body());
            messageSqs.setReceiptHandle(message.receiptHandle());
            messageSqs.setQueueUrl(queueUrl);

            messageSqsList.add(messageSqs);
        }

        return messageSqsList;
    }
}
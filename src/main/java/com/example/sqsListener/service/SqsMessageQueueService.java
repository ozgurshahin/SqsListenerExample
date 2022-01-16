package com.example.sqsListener.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SqsMessageQueueService {

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SqsMessageQueueService(@Qualifier("amazonSQSAsync") final AmazonSQSAsync amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQS);
    }

    public void sendMessage(String queueName, String queueMessage) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, "TestSqsGroupId");
        headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, "TestDeduplicationId");
        queueMessagingTemplate.convertAndSend(queueName, MessageBuilder.withPayload(queueMessage).build(), headers);
    }
}

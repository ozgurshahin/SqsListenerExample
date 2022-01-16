package com.example.sqsListener.listener;

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class FifoQueueListener {
    @SqsListener(value = "http://localhost:4591/queue/SqsListener.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listen(String message) {
        System.out.println("Ipn was listened!" + message);
    }
}

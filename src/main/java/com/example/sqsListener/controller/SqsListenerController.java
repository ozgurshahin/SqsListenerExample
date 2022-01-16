package com.example.sqsListener.controller;

import com.example.sqsListener.service.SqsMessageQueueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SqsListenerController {
    private final SqsMessageQueueService sqsMessageQueueService;

    public SqsListenerController(SqsMessageQueueService sqsMessageQueueService) {
        this.sqsMessageQueueService = sqsMessageQueueService;
    }

    @PostMapping("/sendSqsMessage")
    public String sendSqsMessage(@RequestBody String request) {
        try {
            sqsMessageQueueService.sendMessage("http://localhost:4591/queue/SqsListener.fifo", request);
            return "Sqs Message has been sent";
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}

package br.com.brincando.queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QueueSenderService {

    @Value("${aws.sqs.queue.consumer}")
    private String consumerUrl;

    private final QueueClient queueClient;

    public QueueSenderService(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    public void send(String message) {
        QueueMessage queueMessage = new QueueMessage(message, Queue.QUEUE_TEST, this.consumerUrl);
        this.queueClient.sendMessage(queueMessage);
    }
}

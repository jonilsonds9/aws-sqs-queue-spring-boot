package br.com.brincando.queue;

import org.springframework.stereotype.Service;

@Service
public class QueueConsumerService {

    public QueueResult consumer(QueueMessage message) {
        return QueueResult.SUCCESS;
    }
}

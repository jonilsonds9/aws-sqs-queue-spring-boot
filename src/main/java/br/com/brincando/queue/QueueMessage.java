package br.com.brincando.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.UUID;

public class QueueMessage implements Serializable {

    private final Queue queue;
    private final UUID uuid;
    private final String body;
    private final String consumerUrl;

    public QueueMessage(String mensagem, Queue queue, String consumerUrl) {
        this.queue = queue;
        this.uuid = UUID.randomUUID();
        this.body = "Mensagem: %s - UUID: %s".formatted(mensagem, this.uuid);
        this.consumerUrl = consumerUrl;
    }

    public Queue getQueue() {
        return queue;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getBody() {
        return body;
    }

    public String getConsumerUrl() {
        return consumerUrl;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getQueueUrl() {
        return queue.getQueueUrl();
    }
}

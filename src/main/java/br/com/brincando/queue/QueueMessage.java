package br.com.brincando.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.UUID;

public class QueueMessage implements Serializable {

    private final Queue queue;
    private final UUID uuid;
    private final String body;

    public QueueMessage(String mensagem, Queue queue) {
        this.queue = queue;
        this.uuid = UUID.randomUUID();
        this.body = "Mensagem: %s - UUID: %s".formatted(mensagem, this.uuid);
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

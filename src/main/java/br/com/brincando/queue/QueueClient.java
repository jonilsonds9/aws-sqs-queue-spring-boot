package br.com.brincando.queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueueClient {

    @Value("${aws.sqs.queue.region}")
    private String queueRegion;

    private final QueueAwsCredentialsProvider credentialsProvider;

    public QueueClient(QueueAwsCredentialsProvider queueAwsCredentialsProvider) {
        this.credentialsProvider = queueAwsCredentialsProvider;
    }

    public void sendMessage(QueueMessage queueMessage, String consumerUrl) {
        SendMessageRequest sendMessageRequest = this.generateSendMessageRequest(queueMessage, consumerUrl);

        try (SqsClient sqsClient = getSqsClient()) {
            sqsClient.sendMessage(sendMessageRequest);
        } catch (SdkException e) {
            System.out.println("Erro ou enviar mensagens!");
            e.printStackTrace();
        }
    }

    private SqsClient getSqsClient() {
        return SqsClient.builder()
                .region(Region.of(this.queueRegion))
                .region(Region.US_EAST_1)
                .credentialsProvider(this.credentialsProvider)
                .build();
    }

    private SendMessageRequest generateSendMessageRequest(QueueMessage queueMessage, String consumerUrl) {
        MessageAttributeValue consumeUrlAttributeValue = MessageAttributeValue.builder()
                .stringValue(consumerUrl)
                .dataType("String")
                .build();

        return SendMessageRequest.builder()
                .queueUrl(queueMessage.getQueueUrl())
                .messageBody(queueMessage.toJson())
                .messageAttributes(Map.of("consumerUrl", consumeUrlAttributeValue))
                .build();
    }
}

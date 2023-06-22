package br.com.brincando.queue;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.*;

@Component
public class QueueAwsCredentialsProvider implements AwsCredentialsProvider {

    @Override
    public AwsCredentials resolveCredentials() {
        try (DefaultCredentialsProvider provider = DefaultCredentialsProvider.create()) {
            return provider.resolveCredentials();
        }
    }
}


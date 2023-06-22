package br.com.brincando.queue;

import org.springframework.stereotype.Service;

@Service
public class QueueConsumerService {

    public QueueResult consumer(QueueMessage message) {
        if (this.shouldAnswerSuccess()) {
            return QueueResult.SUCCESS;
        }
        return QueueResult.FAIL;
    }

    public boolean shouldAnswerSuccess() {
        // Código besta pra simular resposta com sucesso ou falha
        int min = 1;
        int max = 10;
        var result = (int) ((Math.random() * (max - min)) + min);
        return result % 2 == 0;
    }
}

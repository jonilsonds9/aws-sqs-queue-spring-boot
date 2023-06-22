package br.com.brincando.queue;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueueController {

    private final QueueSenderService queueSenderService;
    private final QueueConsumerService queueConsumerService;

    public QueueController(QueueSenderService queueSenderService, QueueConsumerService queueConsumerService) {
        this.queueSenderService = queueSenderService;
        this.queueConsumerService = queueConsumerService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Home");
    }

    @GetMapping("/send/{message}")
    public ResponseEntity<String> send(@PathVariable String message) {
        this.queueSenderService.send(message);
        return ResponseEntity.ok("Mensagem enviada!");
    }

    @PostMapping("/consumer")
    public ResponseEntity<String> consumer(@RequestBody QueueMessage message) {
        QueueResult result = this.queueConsumerService.consumer(message);
        return ResponseEntity.status(result.getStatus()).build();
    }
}

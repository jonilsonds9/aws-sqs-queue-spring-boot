package br.com.brincando.queue;

import org.springframework.http.HttpStatus;

public enum QueueResult {

    SUCCESS(HttpStatus.OK),
    FAIL(HttpStatus.BAD_GATEWAY);

    private final HttpStatus status;

    QueueResult(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

package br.com.brincando.queue;

enum Queue {

    QUEUE_TEST("bla");

    private final String queueName;

    Queue(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }
}

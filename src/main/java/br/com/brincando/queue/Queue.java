package br.com.brincando.queue;

enum Queue {

    QUEUE_TEST("aws-simple-queue");

    private final String queueName;

    Queue(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getQueueUrl() {
        return "https://sqs.us-east-1.amazonaws.com/1234/%s".formatted(this.queueName);
    }
}

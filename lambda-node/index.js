import { handler } from "./handler.js";

const event = {
  Records: [{
    messageId: '03c3d6be-f2f6-4093-9438-9b06201cac7c',
    body: '{"queue":"QUEUE_TEST","uuid":"baab7cb0-c029-4ac0-963e-9dd3e8a5b51d","body":"Mensagem: teste - UUID: baab7cb0-c029-4ac0-963e-9dd3e8a5b51d","queueUrl":"https://sqs.us-east-1.amazonaws.com/1234/aws-simple-queue"}',
    messageAttributes: {
      consumerUrl: {
        stringValue: 'https://bla.ngrok-free.app',
        stringListValues: [],
        binaryListValues: [],
        dataType: 'String'
      }
    }
  }]
}

await handler(event);
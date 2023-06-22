/* global fetch */
export const handler = async function(event, context) {
  const batchItemFailures =[];

  async function execute(record) {
    const {
      messageAttributes: {
        consumerUrl: { stringValue: url }
      },
      body
    } = record;

    try {
      const response = await fetch(`${url}/consumer`, {
        method: 'post',
        body: body,
        headers: { "Content-Type": "application/json" },
      });

      if (response.status !== 200) {
        throw Error(`Status code: ${response.status}`);
      } else {
        console.log(`SQS message consumed successfully: ${body}`);
      }
    } catch (error) {
      console.log(`Error in processing SQS consumer: ${body} - ${error}`);
      batchItemFailures.push({itemIdentifier: record.messageId});
    }
  }

  async function filterAllRecords(records){
    for (const record of records) {
      await execute(record)
    }
  }

  await filterAllRecords(event.Records)

  return { batchItemFailures };
}
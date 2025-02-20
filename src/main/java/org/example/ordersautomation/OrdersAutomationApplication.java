package org.example.ordersautomation;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.example.ordersautomation.implementations.CoreTransactionDetails;
import org.example.ordersautomation.workflows.MoneyTransferWorkflow;
import org.example.ordersautomation.workflows.TransactionDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdersAutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersAutomationApplication.class, args);

        WorkflowServiceStubs serviceStubs = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(serviceStubs);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("MONEY_TRANSFER_TASK_QUEUE")
                .setWorkflowId("money-transfer-workflow")
                .build();

        MoneyTransferWorkflow workflow = client.newWorkflowStub(MoneyTransferWorkflow.class, options);

        String referenceId = "1234-reference";
        String senderAccountId = "1234-sender";
        String receiverAccountId = "1234-receiver";
        int amount = 10_000;

        TransactionDetails details = new CoreTransactionDetails(
                senderAccountId,
                receiverAccountId,
                referenceId,
                amount
        );

        WorkflowExecution execution = WorkflowClient.start(workflow::transfer, details);

        System.out.println("Workflow started: " + execution.getWorkflowId());
    }

}

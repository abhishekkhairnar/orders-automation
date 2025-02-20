package org.example.ordersautomation.implementations;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import org.example.ordersautomation.workflows.AccountActivity;
import org.example.ordersautomation.workflows.MoneyTransferWorkflow;
import org.example.ordersautomation.workflows.TransactionDetails;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MoneyTransfer implements MoneyTransferWorkflow {

    enum TransactionType {
        WITHDRAW,
        DEPOSIT,
        REFUND,
        TRANSFER
    }

    private final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(10))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(5)
            .build();

    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            .setRetryOptions(retryOptions)
            .setStartToCloseTimeout(Duration.ofSeconds(2))
            .setScheduleToCloseTimeout(Duration.ofSeconds(10))
            .build();

    private final Map<String, ActivityOptions> perActivityMethodOptions = new HashMap<>() {{
        put(
                TransactionType.WITHDRAW.toString(),
                ActivityOptions.newBuilder()
                        .setHeartbeatTimeout(Duration.ofSeconds(5))
                        .build()
        );
    }};

    private final AccountActivity accountantActivityStub = Workflow.newActivityStub(
            AccountActivity.class,
            defaultActivityOptions,
            perActivityMethodOptions
    );

    @Override
    public void transfer(TransactionDetails transaction) {

        String senderAccountId = transaction.senderAccountId();
        String receiverAccountId = transaction.receiverAccountId();
        String transactionId = transaction.transactionId();
        int amount = transaction.transferAmount();

        // withdraw
        try {
            accountantActivityStub.withdraw(senderAccountId, transactionId, amount);
            System.out.println(amount + " Debited from " + senderAccountId);
        } catch (Exception e) {
            System.out.println("Something went wrong while WITHDRAW: " + e.getMessage());
        }

        // deposit
        try {
            accountantActivityStub.deposit(receiverAccountId, transactionId, amount);
            System.out.println("Transaction Succeeded: " + transaction.transactionId());
        } catch (Exception e) {
            System.out.println("Something went wrong while DEPOSIT: " + e.getMessage());
        }

        // refund
        try {
            accountantActivityStub.refund(senderAccountId, transactionId, amount);
            System.out.println("Refund Succeeded: " + transaction.transactionId());
        } catch (Exception e) {
            System.out.println("Something went wrong while REFUND: " + e.getMessage());
        }
    }
}

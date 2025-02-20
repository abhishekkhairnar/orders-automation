package org.example.ordersautomation.implementations;

import org.example.ordersautomation.workflows.TransactionDetails;

public class CoreTransactionDetails implements TransactionDetails {
    private String senderAccountId;
    private String receiverAccountId;
    private String transactionId;
    private int transferAmount;

    public CoreTransactionDetails() {}

    public CoreTransactionDetails(
            String senderAccountId,
            String receiverAccountId,
            String transactionId,
            int transferAmount
    ) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.transactionId = transactionId;
        this.transferAmount = transferAmount;
    }

    @Override
    public String senderAccountId() {
        return senderAccountId;
    }

    @Override
    public String receiverAccountId() {
        return receiverAccountId;
    }

    @Override
    public String transactionId() {
        return transactionId;
    }

    @Override
    public int transferAmount() {
        return transferAmount;
    }
}

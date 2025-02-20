package org.example.ordersautomation.workflows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.ordersautomation.implementations.CoreTransactionDetails;

@JsonDeserialize(as = CoreTransactionDetails.class)
public interface TransactionDetails {
    String senderAccountId();
    String receiverAccountId();
    String transactionId();
    int transferAmount();
}

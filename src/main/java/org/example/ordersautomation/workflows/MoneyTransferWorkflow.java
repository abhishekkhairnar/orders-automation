package org.example.ordersautomation.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {
    @WorkflowMethod
    void transfer(TransactionDetails transaction);
}

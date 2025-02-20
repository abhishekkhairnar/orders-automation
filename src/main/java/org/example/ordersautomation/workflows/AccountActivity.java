package org.example.ordersautomation.workflows;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AccountActivity {
    @ActivityMethod
    void withdraw(String accountId, String referenceId, int amount);

    @ActivityMethod
    void deposit(String accountId, String referenceId, int amount);

    @ActivityMethod
    void refund(String accountId, String referenceId, int amount);

    @ActivityMethod
    void transfer(String senderAccountId, String receiverAccountId, int amount);
}

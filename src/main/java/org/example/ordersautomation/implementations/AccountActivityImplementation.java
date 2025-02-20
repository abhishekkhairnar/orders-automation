package org.example.ordersautomation.implementations;

import org.example.ordersautomation.workflows.AccountActivity;

public class AccountActivityImplementation implements AccountActivity {
    @Override
    public void withdraw(String accountId, String referenceId, int amount) {
        System.out.println("Withdrawing " + amount + " from " + accountId + " to " + referenceId);
        System.out.flush();
    }

    @Override
    public void deposit(String accountId, String referenceId, int amount) {
        System.out.println("Deposit " + amount + " from " + accountId + " to " + referenceId);
        System.out.flush();
    }

    @Override
    public void refund(String accountId, String referenceId, int amount) {
        System.out.println("Refund " + amount + " from " + accountId + " to " + referenceId);
        System.out.flush();
    }

    @Override
    public void transfer(String senderAccountId, String receiverAccountId, int amount) {
        System.out.println("Transfer " + amount + " from " + senderAccountId + " to " + receiverAccountId);
        System.out.flush();
    }
}

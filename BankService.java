package com.bank.bankmanagementsytem;
import java.util.HashMap;

public class BankService {

    private HashMap<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accNo, String name, double balance) {
        if (accounts.containsKey(accNo)) {
            System.out.println("❌ Account already exists!");
            return;
        }
        accounts.put(accNo, new BankAccount(accNo, name, balance));
        System.out.println("✅ Account created successfully!");
    }

    public BankAccount getAccount(String accNo) {
        return accounts.get(accNo);
    }
}

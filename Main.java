package com.bank.bankmanagementsytem;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        while (true) {
            System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Menu choice validation
            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid choice! Please enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            try {
                switch (choice) {

                    // ================= CREATE ACCOUNT =================
                    case 1:
                        System.out.print("Enter Account Number (12 digits): ");
                        String accNo = sc.next().trim();

                        // STRICT 12-digit validation
                        if (!accNo.matches("^\\d{12}$")) {
                            System.out.println("❌ Invalid Account Number! Please enter EXACTLY 12 digits.");
                            continue;
                        }

                        System.out.print("Enter Account Holder Name: ");
                        sc.nextLine(); // clear buffer
                        String name = sc.nextLine();

                        System.out.print("Enter Initial Balance: ");
                        if (!sc.hasNextDouble()) {
                            System.out.println("❌ Invalid balance! Please enter numeric value.");
                            sc.next();
                            continue;
                        }

                        double balance = sc.nextDouble();
                        if (balance < 0) {
                            System.out.println("❌ Balance cannot be negative.");
                            continue;
                        }

                        service.createAccount(accNo, name, balance);
                        break;

                    // ================= DEPOSIT =================
                    case 2:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next().trim();

                        if (!accNo.matches("^\\d{12}$")) {
                            System.out.println("❌ Invalid Account Number format.");
                            continue;
                        }

                        BankAccount acc = service.getAccount(accNo);
                        if (acc == null) {
                            System.out.println("❌ Account not found!");
                            continue;
                        }

                        System.out.print("Enter Deposit Amount: ");
                        if (!sc.hasNextDouble()) {
                            System.out.println("❌ Invalid amount! Enter numbers only.");
                            sc.next();
                            continue;
                        }

                        double deposit = sc.nextDouble();
                        if (deposit <= 0) {
                            System.out.println("❌ Amount must be greater than zero.");
                            continue;
                        }

                        acc.deposit(deposit);
                        System.out.println("✅ Amount deposited successfully!");
                        break;

                    // ================= WITHDRAW =================
                    case 3:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next().trim();

                        if (!accNo.matches("^\\d{12}$")) {
                            System.out.println("❌ Invalid Account Number format.");
                            continue;
                        }

                        acc = service.getAccount(accNo);
                        if (acc == null) {
                            System.out.println("❌ Account not found!");
                            continue;
                        }

                        System.out.print("Enter Withdraw Amount: ");
                        if (!sc.hasNextDouble()) {
                            System.out.println("❌ Invalid amount! Enter numbers only.");
                            sc.next();
                            continue;
                        }

                        double withdraw = sc.nextDouble();
                        if (withdraw <= 0) {
                            System.out.println("❌ Amount must be greater than zero.");
                            continue;
                        }

                        acc.withdraw(withdraw);
                        System.out.println("✅ Amount withdrawn successfully!");
                        break;

                    // ================= CHECK BALANCE =================
                    case 4:
                        System.out.print("Enter Account Number: ");
                        accNo = sc.next().trim();

                        if (!accNo.matches("^\\d{12}$")) {
                            System.out.println("❌ Invalid Account Number format.");
                            continue;
                        }

                        acc = service.getAccount(accNo);
                        if (acc == null) {
                            System.out.println("❌ Account not found!");
                        } else {
                            acc.displayDetails();
                        }
                        break;

                    // ================= EXIT =================
                    case 5:
                        System.out.println("🙏 Thank you for using Bank Management System!");
                        System.exit(0);

                    default:
                        System.out.println("❌ Invalid menu option!");
                }

            } catch (InsufficientBalanceException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

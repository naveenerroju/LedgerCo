package com.naveen;

import java.util.Scanner;

/**
 * @author Naveen Kumar
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Ledger ledger = new Ledger();

    public static void main(String[] args) {

        while (scanner.hasNext()) {
            String command = scanner.next();
            processTheCommand(command);
        }
        scanner.close();
    }

    private static void processTheCommand(String command) {
        switch (command) {
            case "LOAN":
                String bankName = scanner.next();
                String borrowerName = scanner.next();
                double principal = scanner.nextDouble();
                int termInYears = scanner.nextInt();
                double rateOfInterest = scanner.nextDouble();
                ledger.processLoan(bankName, borrowerName, principal, termInYears, rateOfInterest);
                break;
            case "PAYMENT":
                bankName = scanner.next();
                borrowerName = scanner.next();
                double lumpSumAmount = scanner.nextDouble();
                int emiNumber = scanner.nextInt();
                ledger.processPayment(bankName, borrowerName, lumpSumAmount, emiNumber);
                break;
            case "BALANCE":
                bankName = scanner.next();
                borrowerName = scanner.next();
                emiNumber = scanner.nextInt();
                ledger.processBalance(bankName, borrowerName, emiNumber);
                break;
        }
    }

}
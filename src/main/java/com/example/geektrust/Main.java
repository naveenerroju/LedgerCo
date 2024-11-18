package com.example.geektrust;

import java.io.*;
import java.util.Scanner;

/**
 * @author Naveen Kumar
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java LoanSystem <input_file>");
            return;
        }

        String filePath = args[0];
        Ledger Ledger = new Ledger();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processCommand(line, Ledger);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }

    private static void processCommand(String commandLine, Ledger ledger) {
        String[] parts = commandLine.split(" ");
        String command = parts[0];

        switch (command) {
            case "LOAN":
                processLoan(parts, ledger);
                break;
            case "PAYMENT":
                processPayment(parts, ledger);
                break;
            case "BALANCE":
                processBalance(parts, ledger);
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

    private static void processLoan(String[] parts, Ledger ledger) {
        String bankName = parts[1];
        String borrowerName = parts[2];
        int principal = Integer.parseInt(parts[3]);
        int years = Integer.parseInt(parts[4]);
        double rateOfInterest = Double.parseDouble(parts[5]);

        ledger.processLoanCommand(bankName, borrowerName, principal, years, rateOfInterest);
    }

    private static void processPayment(String[] parts, Ledger ledger) {
        String bankName = parts[1];
        String borrowerName = parts[2];
        int lumpSumAmount = Integer.parseInt(parts[3]);
        int emiNumber = Integer.parseInt(parts[4]);

        ledger.processPaymentCommand(bankName, borrowerName, lumpSumAmount, emiNumber);
    }

    private static void processBalance(String[] parts, Ledger ledger) {
        String bankName = parts[1];
        String borrowerName = parts[2];
        int emiNumber = Integer.parseInt(parts[3]);

        ledger.processBalanceCommand(bankName, borrowerName, emiNumber);
    }
}

package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;

public class Ledger {
    private Map<String, Loan> loanRecords = new HashMap<>();

    public void LoanManager() {
        loanRecords = new HashMap<>();
    }

    public void processLoanCommand(String bankName, String borrowerName, int principal, int years, double rateOfInterest) {
        Loan loan = new Loan(bankName, borrowerName, principal, years, rateOfInterest);
        loanRecords.put(generateKey(bankName, borrowerName), loan);
    }

    public void processPaymentCommand(String bankName, String borrowerName, int lumpSumAmount, int emiNumber) {
        Loan loan = loanRecords.get(generateKey(bankName, borrowerName));
        if (loan != null) {
            loan.makeLumpSumPayment(lumpSumAmount, emiNumber);
        }
    }

    public void processBalanceCommand(String bankName, String borrowerName, int emiNumber) {
        Loan loan = loanRecords.get(generateKey(bankName, borrowerName));
        if (loan != null) {
            System.out.println(loan.getBalance(emiNumber));
        }
    }

    private String generateKey(String bankName, String borrowerName) {
        return bankName + "-" + borrowerName;
    }
}
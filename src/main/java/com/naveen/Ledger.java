package com.naveen;

import java.util.HashMap;
import java.util.Map;

public class Ledger {
    Map<String, Loan> loans;

    public Ledger() {
        this.loans = new HashMap<>();
    }

    public void processLoan(String bankName, String borrowerName, double principal, int termInYears, double rateOfInterest) {
        Loan loan = new Loan(bankName, borrowerName, principal, termInYears, rateOfInterest);
        String key = bankName + "-" + borrowerName;
        loans.put(key, loan);
    }

    public void processPayment(String bankName, String borrowerName, double lumpSumAmount, int emiNumber) {
        String key = bankName + "-" + borrowerName;
        if (loans.containsKey(key)) {
            loans.get(key).makeLumpSumPayment(emiNumber, lumpSumAmount);
        }
    }

    public void processBalance(String bankName, String borrowerName, int emiNumber) {
        String key = bankName + "-" + borrowerName;
        if (loans.containsKey(key)) {
            Loan loan = loans.get(key);
            double totalPaid = loan.getTotalPaidUntilEmi(emiNumber);
            int remainingEmis = loan.getRemainingEmis(emiNumber);
            System.out.println(bankName + " " + borrowerName + " " + (int) totalPaid + " " + remainingEmis);
        }
    }
}
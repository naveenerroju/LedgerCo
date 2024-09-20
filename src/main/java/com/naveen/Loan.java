package com.naveen;

import java.util.HashMap;
import java.util.Map;

public class Loan {
    String bankName;
    String borrowerName;
    double principal;
    double rateOfInterest;
    int termInYears;
    double totalAmount; // A = P + I
    double emiAmount;
    int totalEmis;
    double amountPaidSoFar;
    Map<Integer, Double> lumpSumPayments; // EMI number -> Lump sum paid

    public Loan(String bankName, String borrowerName, double principal, int termInYears, double rateOfInterest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.termInYears = termInYears;
        this.rateOfInterest = rateOfInterest;
        this.lumpSumPayments = new HashMap<>();
        calculateLoanDetails();
    }

    private void calculateLoanDetails() {
        double interest = (principal * termInYears * rateOfInterest) / 100;
        this.totalAmount = principal + interest;
        this.totalEmis = (int) Math.ceil(termInYears * 12); // Convert years to months
        this.emiAmount = Math.ceil(totalAmount / totalEmis);
        this.amountPaidSoFar = 0;
    }

    public void makeLumpSumPayment(int emiNumber, double amount) {
        lumpSumPayments.put(emiNumber, lumpSumPayments.getOrDefault(emiNumber, 0.0) + amount);
    }

    public double getTotalPaidUntilEmi(int emiNumber) {
        double totalPaid = emiNumber * emiAmount;
        for (Map.Entry<Integer, Double> entry : lumpSumPayments.entrySet()) {
            if (entry.getKey() <= emiNumber) {
                totalPaid += entry.getValue();
            }
        }
        return Math.min(totalPaid, totalAmount);
    }

    public int getRemainingEmis(int emiNumber) {
        double totalPaid = getTotalPaidUntilEmi(emiNumber);
        double remainingAmount = totalAmount - totalPaid;
        return (int) Math.ceil(remainingAmount / emiAmount);
    }
}

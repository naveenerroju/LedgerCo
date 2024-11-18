package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;

public class Loan {
    private final String bankName;
    private final String borrowerName;
    private final int principal;
    private final int loanTermInYears;
    private final double rateOfInterest;
    private final double totalAmount;
    private final int emiAmount;
    private double remainingAmount;
    private Map<Integer, Integer> lumpSumPayments; // EMI number -> Lump sum paid

    public Loan(String bankName, String borrowerName, int principal, int loanTermInYears, double rateOfInterest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.loanTermInYears = loanTermInYears;
        this.rateOfInterest = rateOfInterest;
        this.totalAmount = calculateTotalAmount(principal, loanTermInYears, rateOfInterest);
        this.emiAmount = calculateEMI(this.totalAmount, loanTermInYears);
        this.remainingAmount = this.totalAmount;
        this.lumpSumPayments = new HashMap<>();
}

private double calculateTotalAmount(int principal, int loanTermInYears, double rateOfInterest) {
    double interest = (principal * loanTermInYears * rateOfInterest) / 100;
    return principal + interest;
}

private int calculateEMI(double totalAmount, int loanTermInYears) {
    int numberOfMonths = loanTermInYears * 12;
    return (int) Math.ceil(totalAmount / numberOfMonths);
}

public void makeLumpSumPayment(int lumpSumAmount, int emiNumber) {
    lumpSumPayments.put(emiNumber, lumpSumPayments.getOrDefault(emiNumber, 0) + lumpSumAmount);
}

public String getBalance(int emiNumber) {
    double totalPaid = calculateTotalPaid(emiNumber);
    double remainingAmount = this.totalAmount - totalPaid;
    int remainingEMIs = (remainingAmount > 0) ? (int) Math.ceil(remainingAmount / emiAmount) : 0;
    return String.format("%s %s %.0f %d", bankName, borrowerName, totalPaid, remainingEMIs);
}

private double calculateTotalPaid(int emiNumber) {
    double totalPaidFromEMIs = Math.min(emiAmount * emiNumber, totalAmount);
    int lumpSumTotal = lumpSumPayments.entrySet().stream()
            .filter(entry -> entry.getKey() <= emiNumber)
            .mapToInt(Map.Entry::getValue)
            .sum();
    return totalPaidFromEMIs + lumpSumTotal;
}
}

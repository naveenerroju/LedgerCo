package com.naveen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LedgerTest {

    private Ledger ledger;

    @BeforeEach
    void setUp() {
        ledger = new Ledger();
    }

    @Test
    void testLoanCreationAndBalanceQuery() {
        ledger.processLoan("IDIDI", "Dale", 10000, 5, 4);

        assertEquals("IDIDI Dale 1000 55", getBalanceOutput("IDIDI", "Dale", 5));

        assertEquals("IDIDI Dale 8000 20", getBalanceOutput("IDIDI", "Dale", 40));
    }

    @Test
    void testLoanWithLumpSumPayment() {
        ledger.processLoan("MBI", "Harry", 10000, 3, 7);
        ledger.processPayment("MBI", "Harry", 5000, 10);

        assertEquals("MBI Harry 9044 10", getBalanceOutput("MBI", "Harry", 12));

        assertEquals("MBI Harry 12100 0", getBalanceOutput("MBI", "Harry", 30));
    }

    @Test
    void testMultipleLoansAndPayments() {
        ledger.processLoan("IDIDI", "Dale", 5000, 1, 6);
        ledger.processLoan("MBI", "Harry", 10000, 3, 7);
        ledger.processLoan("UON", "Shelly", 15000, 2, 9);

        ledger.processPayment("IDIDI", "Dale", 1000, 5);
        ledger.processPayment("MBI", "Harry", 5000, 10);
        ledger.processPayment("UON", "Shelly", 7000, 12);

        assertEquals("IDIDI Dale 1326 9", getBalanceOutput("IDIDI", "Dale", 3));

        assertEquals("IDIDI Dale 3652 4", getBalanceOutput("IDIDI", "Dale", 6));

        assertEquals("UON Shelly 15856 3", getBalanceOutput("UON", "Shelly", 12));

        assertEquals("MBI Harry 9044 10", getBalanceOutput("MBI", "Harry", 12));
    }

    @Test
    void testZeroEmiBalance() {
        ledger.processLoan("UON", "Shelly", 2000, 1, 8);

        assertEquals("UON Shelly 0 12", getBalanceOutput("UON", "Shelly", 0));
    }

    @Test
    void testEarlyCompletionWithLumpSum() {
        ledger.processLoan("IDIDI", "Dale", 10000, 5, 4);
        ledger.processPayment("IDIDI", "Dale", 11000, 1);

        assertEquals("IDIDI Dale 11200 4", getBalanceOutput("IDIDI", "Dale", 1));
    }

    private String getBalanceOutput(String bankName, String borrowerName, int emiNumber) {
        ledger.processBalance(bankName, borrowerName, emiNumber);
        return bankName + " " + borrowerName + " " + (int) ledger.loans.get(bankName + "-" + borrowerName).getTotalPaidUntilEmi(emiNumber) + " " + ledger.loans.get(bankName + "-" + borrowerName).getRemainingEmis(emiNumber);
    }
}

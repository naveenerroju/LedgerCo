# The Ledger Co. - Loan and EMI Management System

## Overview

**The Ledger Co.** is a marketplace for banks to lend money to borrowers. This project implements a system to track loan disbursements, EMI payments, lump sum payments, and remaining EMIs.

The system performs the following tasks:
1. Process loans between banks and borrowers.
2. Track EMI payments over a period of time.
3. Handle lump sum payments made by borrowers.
4. Provide the current balance (amount paid and EMIs left) at any given point in the loan cycle.

## Problem Statement

The system takes input commands to:
1. Record a loan with details like bank name, borrower name, loan amount, tenure, and interest rate.
2. Record a lump sum payment made after a certain EMI.
3. Retrieve the balance for a specific EMI, showing the total amount paid so far and remaining EMIs.

### Formulae Used:
- **Interest:**  
  \[
  I = P \times N \times R / 100
  \]
  Where:
    - `P` is the Principal Amount
    - `N` is the number of years
    - `R` is the rate of interest

- **Total Amount to repay:**  
  \[
  A = P + I
  \]

- **EMI (Monthly Installment):**  
  \[
  EMI = \lceil A / (N \times 12) \rceil
  \]
  Where:
    - `A` is the total amount
    - `N` is the number of years
    - The result is always rounded up to the nearest integer.

- **Remaining EMIs after a lump sum payment:**  
  Recalculate EMIs based on the remaining balance after deducting lump sum payments.

## Input Commands

The system accepts the following commands:

1. **LOAN**: Records a loan disbursement.
    - **Format**: `LOAN BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST`
    - **Example**: `LOAN IDIDI Dale 10000 5 4`

2. **PAYMENT**: Records a lump sum payment after a specified EMI.
    - **Format**: `PAYMENT BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO`
    - **Example**: `PAYMENT MBI Harry 1000 5`

3. **BALANCE**: Retrieves the current balance for a specific EMI number.
    - **Format**: `BALANCE BANK_NAME BORROWER_NAME EMI_NO`
    - **Example**: `BALANCE IDIDI Dale 5`

### Assumptions

1. Payments will be made every month through EMIs until the loan is fully repaid.
2. Lump sum payments can be made at any time before the loan period ends.
3. If the remaining amount to repay is less than the EMI, the EMI will be adjusted accordingly for the final payment.

## Output Format

For each balance query, the output will show:
- `BANK_NAME BORROWER_NAME AMOUNT_PAID NO_OF_EMIS_LEFT`

### Example

#### Input:
```text
LOAN IDIDI Dale 10000 5 4
LOAN MBI Harry 2000 2 2
BALANCE IDIDI Dale 5
BALANCE IDIDI Dale 40
BALANCE MBI Harry 12
BALANCE MBI Harry 0
```
#### Output:
```text
IDIDI Dale 1000 55
IDIDI Dale 8000 20
MBI Harry 1044 12
MBI Harry 0 24

```
### Unit testing
unit tests are provided to validate the code and calculations.
There is 91% of line coverage
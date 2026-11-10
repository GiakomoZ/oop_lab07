package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    private static final int NEGATIVE_AMOUNT = 100;
    private static final int ACCEPTABLE_MESSAGE_LENGTH = 10;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are
    // executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the
     * balance is correctly reduced.
     */
    @Test
    void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), 100);
        bankAccount.chargeManagementFees(mRossi.getUserID());
        final double expectedFees = SimpleBankAccount.MANAGEMENT_FEE + 1 * StrictBankAccount.TRANSACTION_FEE;
        assertEquals(100 - expectedFees, bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    void testNegativeWithdraw() {
        final double previousBalance = bankAccount.getBalance();
        try {
            bankAccount.deposit(mRossi.getUserID(), NEGATIVE_AMOUNT);
        } catch (final IllegalArgumentException e) {
            assertEquals(previousBalance, bankAccount.getBalance()); // No money was withdrawed
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH); // A message with a decent length
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    void testWithdrawingTooMuch() {
        final double previousBalance = bankAccount.getBalance();
        try {
            bankAccount.deposit(mRossi.getUserID(), bankAccount.getBalance() + 1);
        } catch (final IllegalArgumentException e) {
            assertEquals(previousBalance, bankAccount.getBalance()); // No money was withdrawed
            assertNotNull(e.getMessage()); // Non-null message
            assertFalse(e.getMessage().isBlank()); // Not a blank or empty message
            assertTrue(e.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH); // A message with a decent length
        }
    }
}

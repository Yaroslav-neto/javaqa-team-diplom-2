package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BankTest {

    Account fromAccount = new Account();
    Account toAccount = new Account();

    @Test
    public void successfulTransfer() {
        Bank bank = new Bank();

        fromAccount.add(100);
        toAccount.add(50);

        assertTrue(bank.transfer(fromAccount, toAccount, 50));
        assertEquals(50, fromAccount.balance);
        assertEquals(100, toAccount.balance);
    }

    @Test
    public void zeroTransfer() {
        Bank bank = new Bank();

        fromAccount.add(100);
        toAccount.add(50);

        assertFalse(bank.transfer(fromAccount, toAccount, 0));
        assertEquals(100, fromAccount.balance);
        assertEquals(50, toAccount.balance);
    }

    @Test
    public void unsuccessfulTansferZeroBalance() {
        Bank bank = new Bank();

        fromAccount.add(0);
        toAccount.add(50);

        assertFalse(bank.transfer(fromAccount, toAccount, 50));
        assertEquals(0, fromAccount.balance);
        assertEquals(50, toAccount.balance);
    }

    @Test
    public void successfulTransferOfZeroSecondAccount() {
        Bank bank = new Bank();

        fromAccount.add(100);
        toAccount.add(0);

        assertTrue(bank.transfer(fromAccount, toAccount, 50));
        assertEquals(50, fromAccount.balance);
        assertEquals(50, toAccount.balance);
    }

    @Test
    public void unsuccessfulTransferInsufficientBalance() {
        Bank bank = new Bank();

        fromAccount.add(50);
        toAccount.add(50);

        assertFalse(bank.transfer(fromAccount, toAccount, 100));
        assertEquals(50, fromAccount.balance);
        assertEquals(50, toAccount.balance);
    }

    @Test
    public void negativeTransfer() {
        Bank bank = new Bank();

        fromAccount.add(100);
        toAccount.add(50);

        assertFalse(bank.transfer(fromAccount, toAccount, -50));
        assertEquals(100, fromAccount.balance);
        assertEquals(50, toAccount.balance);
    }
}
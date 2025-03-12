package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    // тесты для saved счетов
    @Test //успешный перевод в пределах лимитов
    public void shouldSuccessfulTransferForSavingAccount() {
        SavingAccount accountFrom = new SavingAccount(1000, 0, 10000, 10);
        SavingAccount accountTo = new SavingAccount(2000, 2000, 20000, 20);
        Bank bank = new Bank();
        bank.transfer(accountFrom, accountTo, 1000);

        assertEquals(0, accountFrom.getBalance());
        assertEquals(3000, accountTo.getBalance());
    }

    @Test //перевод с выходом за пределы минимального баланса From
    public void shouldTransferForSavingAccountFromLessMinBalance() {
        SavingAccount accountFrom = new SavingAccount(1000, 1000, 10000, 10);
        SavingAccount accountTo = new SavingAccount(2000, 2000, 20000, 20);
        Bank bank = new Bank();
        assertFalse(bank.transfer(accountFrom, accountTo, 1000));
    }

    @Test //перевод с выходом на предел минимального баланса From
    public void shouldTransferForSavingAccountFromEqualMinBalanceFrom() {
        SavingAccount accountFrom = new SavingAccount(2000, 1000, 10000, 10);
        SavingAccount accountTo = new SavingAccount(2000, 2000, 20000, 20);
        Bank bank = new Bank();

        bank.transfer(accountFrom, accountTo, 1000);

        assertEquals(1000, accountFrom.getBalance());
        assertEquals(3000, accountTo.getBalance());
    }

    @Test //перевод с выходом на предел максимального баланса To
    public void shouldTransferForSavingAccountFromEqualMaxBalanceTo() {
        SavingAccount accountFrom = new SavingAccount(2000, 1000, 10000, 10);
        SavingAccount accountTo = new SavingAccount(2000, 2000, 3000, 20);
        Bank bank = new Bank();

        bank.transfer(accountFrom, accountTo, 1000);

        assertEquals(1000, accountFrom.getBalance());
        assertEquals(3000, accountTo.getBalance());
    }

    @Test // перевод 0
    public void shouldSuccessfulTransferForSavingAccountZero() {
        SavingAccount accountFrom = new SavingAccount(1000, 0, 10000, 10);
        SavingAccount accountTo = new SavingAccount(2000, 2000, 20000, 20);
        Bank bank = new Bank();
        bank.transfer(accountFrom, accountTo, 0);

        assertEquals(1000, accountFrom.getBalance());
        assertEquals(2000, accountTo.getBalance());
    }

    @Test //перевод отрицательной суммы
    public void shouldTransferForSavingAccountNegativeAmount() {
        SavingAccount accountFrom = new SavingAccount(1000, 1000, 10000, 10);
        SavingAccount accountTo = new SavingAccount(2000, 2000, 2000, 20);
        Bank bank = new Bank();
        assertFalse(bank.transfer(accountFrom, accountTo, -1000));
    }

    @Test //перевод с выходом за пределы максимального баланса To
    public void shouldTransferForSavingAccountOverMaxBalanceTo() {
        SavingAccount accountFrom = new SavingAccount(3000, 1000, 10000, 10);
        SavingAccount accountTo = new SavingAccount(1000, 1000, 2000, 20);
        Bank bank = new Bank();
        assertFalse(bank.transfer(accountFrom, accountTo, 2000));
    }

    // переводы между кредитными счетами
    @Test //успешный перевод на пределе кредитного лимита
    public void shouldSuccessfulTransferForCreditAccount() {
        CreditAccount accountFrom = new CreditAccount(1000, 1000, 10);
        CreditAccount accountTo = new CreditAccount(2000, 1000, 20);
        Bank bank = new Bank();
        bank.transfer(accountFrom, accountTo, 2000);

        assertEquals(-1000, accountFrom.getBalance());
        assertEquals(4000, accountTo.getBalance());
    }

    @Test //перевод с выходом за предел кредитного лимита
    public void shouldTransferForCreditAccountFromLessMinLimit() {
        CreditAccount accountFrom = new CreditAccount(1000, 1000, 10);
        CreditAccount accountTo = new CreditAccount(2000, 2000, 20);
        Bank bank = new Bank();
        Assertions.assertFalse(bank.transfer(accountFrom, accountTo, 3000));
    }

    @Test // перевод 0
    public void shouldSuccessfulTransferBetweenSavingAndCreditZero() {
        CreditAccount accountFrom = new CreditAccount(1000, 0, 10);
        CreditAccount accountTo = new CreditAccount(2000, 2000, 20);
        Bank bank = new Bank();
        bank.transfer(accountFrom, accountTo, 0);

        assertEquals(1000, accountFrom.getBalance());
        assertEquals(2000, accountTo.getBalance());
    }

    @Test //перевод отрицательной суммы
    public void shouldTransferForCreditAccountNegativeAmount() {
        CreditAccount accountFrom = new CreditAccount(1000, 1000, 10);
        CreditAccount accountTo = new CreditAccount(2000, 2000, 20);
        Bank bank = new Bank();
        assertFalse(bank.transfer(accountFrom, accountTo, -1000));
    }

    @Test //перевод между обычным и кредитным счетом в перелах лимитов
    public void shouldTransferBetweenSavingAccountAndCreditAccount() {
        SavingAccount accountFrom = new SavingAccount(3000, 1000, 10000, 10);
        CreditAccount accountTo = new CreditAccount(1000, 1000, 20);
        Bank bank = new Bank();
        bank.transfer(accountFrom, accountTo, 1000);
        assertEquals(2000, accountFrom.getBalance());
        assertEquals(2000, accountTo.getBalance());
    }

    @Test //перевод между  кредитным и  обычным счетом в перелах лимитов
    public void shouldTransferBetweenCreditAccountAndSavingAccount() {
        CreditAccount accountFrom = new CreditAccount(1000, 1000, 20);
        SavingAccount accountTo = new SavingAccount(3000, 1000, 10000, 10);

        Bank bank = new Bank();
        bank.transfer(accountFrom, accountTo, 2000);
        assertEquals(-1000, accountFrom.getBalance());
        assertEquals(5000, accountTo.getBalance());
    }

    @Test
    public void testPayMethodInitiallyReturnsFalse() {
        Account account = new Account();
        assertFalse(account.pay(100));
    }

    @Test
    public void testAddMethodInitiallyReturnsFalse() {
        Account account = new Account();
        assertFalse(account.add(100));
    }

    @Test
    public void testYearChangeInitiallyReturnsZero() {
        Account account = new Account();
        assertEquals(0, account.yearChange());
    }

    @Test
    public void testGetBalanceInitiallyIsZero() {
        Account account = new Account();
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testGetRateInitiallyIsZero() {
        Account account = new Account();
        assertEquals(0, account.getRate());
    }

    @Test
    public void testSetRate() {
        Account account = new Account();
        account.setRate(5);
        assertEquals(5, account.getRate());
    }
}
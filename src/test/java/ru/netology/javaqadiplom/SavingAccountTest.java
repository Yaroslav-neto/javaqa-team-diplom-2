package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void minimumBalanceGreaterThanMaximum() { //тест выброс исключения (минимальное значение больше максимума)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 2_000, 1_000, 5);
        });
    }


    @Test
    public void balanceLessThanMinimum() { // тест выброс исключения (баланс ниже минимума)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1_000, 2_000, 3_000, 5);
        });
    }

    @Test
    public void balanceMoreThanMaxmum() { // тест выброс исключения (баланс больше максимума)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(4_000, 2_000, 3_000, 5);
        });
    }

    @Test
    public void negativeMinimumBalance() { // тест выброс исключения (отрицательный минимальный баланс)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_500, -2_000, 3_000, 5);
        });
    }

    @Test
    public void negativeRate() { // тест выброс исключения (отрицательная ставка)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_500, 2_000, 3_000, -5);
        });
    }


    @Test
    public void checkingForPositiveWriteOff() { //позитив
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(1_500, account.getBalance());

    }

    @Test
    public void deductionOfNegativeAmount() { //списание отрицательной суммы
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(-100);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    public void deductionOfZeroAmount() { //списание нулевой суммы
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    public void buyingMoreBalance() { //при покупке больше баланса, баланс не должен меняться
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_500);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    public void purchaseEqualBalancee() { //покупка равная размеру баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(2_000);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    public void purchaseEqualMinimumBalance() { //покупка равная минимальному балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1_000);

        Assertions.assertEquals(1_000, account.getBalance());

    }


    @Test
    public void shouldAddLessThanMaxBalance() { //пополнение в пределах максимальной
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void exceedingMaximumDuringReplenishment() { //пополнение больше максимального
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.add(6_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void zeroDeposit() { //пополнение на ноль
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    public void replenishmentNegativeAmount() { //пополнение на отрицание
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-100);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test
    public void replenishmentEqualMaximumBalance() { //пополнение равной макс балансу
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.add(5_000);

        Assertions.assertEquals(10_000, account.getBalance());

    }

    @Test
    public void accrualAnnualInterest() { //начисление процентов, позитивный тест
        SavingAccount account = new SavingAccount(
                200,
                100,
                10_000,
                15
        );

        Assertions.assertEquals(30, account.yearChange());

    }

    @Test
    public void zeroInterestRate() { //начисление процентов, позитивный тест
        SavingAccount account = new SavingAccount(
                200,
                100,
                10_000,
                0
        );

        Assertions.assertEquals(0, account.yearChange());

    }

    @Test
    public void calculatingBidWithZeroBalance() { // отбрасывание дробной части
        SavingAccount account = new SavingAccount(
                250,
                100,
                10_000,
                15
        );

        Assertions.assertEquals(37, account.yearChange());
    }
}

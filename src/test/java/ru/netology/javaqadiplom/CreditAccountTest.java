package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {

    @Test // начальный баланс 0 пополнение на 3000
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
        //БАГ
        // начальный баланс меньше 0 выброс исключения
    void shouldIllegalArgumentExceptionNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new CreditAccount(-100, 1000, 10));
    }

    @Test
        // кредитный лимит 0 нет исключения
    void shouldCreditLimitNull() {
        assertDoesNotThrow(() -> new CreditAccount(1000, 0, 10));
    }

    @Test
        // БАГ
        //    кредитный лимит меньше 0 выброс исключения
    void shouldCreditLimitLessNull() {
        assertThrows(IllegalArgumentException.class, () -> new CreditAccount(1000, -1, 10));
    }

    @Test
        //БАГ
        //    ставка 0 нет исключения
    void testNullRate() {
        assertDoesNotThrow(() -> new CreditAccount(100, 1000, 0));
    }

    @Test
        //    ставка меньше 0 выброс исключения
    void testLessNullRate() {
        assertThrows(IllegalArgumentException.class, () -> new CreditAccount(100, 1000, -1));
    }

    @Test
        //БАГ
        // успешная покупка
    void testPaySuccessful() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertTrue(account.pay(50));
        assertEquals(50, account.getBalance());
    }

    @Test
        //БАГ
        // неуспешная покупка
    void testPayUnsuccessful() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.pay(1200));
        assertEquals(100, account.getBalance());
    }

    @Test
        //БАГ
        // положительный баланс - процентов нет
    void testYearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(200, 1000, 15);
        assertEquals(0, account.yearChange());
    }

    @Test
        // положительный  баланс - процентов нет
    void testYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(200, 1000, 15);
        assertEquals(0, account.yearChange());
    }

    @Test
        //покупка на отрицательную сумму - 0 реакции
    void testPayNegativeAmount() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.pay(-50));
        assertEquals(100, account.getBalance());
    }

    @Test
        // пополнение на отрицательную сумму - 0 реакции
    void testAddNegativeAmount() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.add(-50));
        assertEquals(100, account.getBalance());
    }

    @Test
        //БАГ
        // Покупка на сумму = лимиту + текущий баланс
    void testPayUpToCreditLimit() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertTrue(account.pay(1100));
        assertEquals(-1000, account.getBalance());
    }

    @Test
        //БАГ
        // покупка больше, чем кредитный лимит + текущий баланс - false
    void testPayOverCreditLimit() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.pay(1101));
        assertEquals(100, account.getBalance());
    }

    @Test
        // Пополнение счета на 0
    void testAddZeroAmount() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.add(0));
        assertEquals(100, account.getBalance());
    }

    @Test
        // Покупка на 0
    void testPayZeroAmount() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.pay(0));
        assertEquals(100, account.getBalance());
    }

    @Test
        // Проверка начисления процентов при нулевом балансе
    void testYearChangeZeroBalance() {
        CreditAccount account = new CreditAccount(0, 1000, 15);
        assertEquals(0, account.yearChange());
    }

    @Test
        // Проверка процентов при балансе близком к нулю
    void testYearChangeSmallBalance() {
        CreditAccount account = new CreditAccount(0, 1000, 15);
        account.pay(1);
        assertEquals(0, account.yearChange());
    }

    @Test
        // пополнение на огромную сумму - баланс не изменится false
    void testAddLargeAmount() {
        CreditAccount account = new CreditAccount(100, 1000, 10);
        assertFalse(account.add((int) Long.MAX_VALUE));
        assertEquals(100, account.getBalance());
    }

    @Test
        //БАГ
        // Проверка расчета процентов при отрицательном балансе
    void shouldYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(0, 1000, 15);
        account.pay(250);
        assertEquals(-37, account.yearChange());
    }

    @Test
        // получение геттеров
    void shouldGetters() {
        CreditAccount account = new CreditAccount(100, 1000, 15);
        assertEquals(100, account.getBalance());
        assertEquals(1000, account.getCreditLimit());
        assertEquals(15, account.getRate());
    }
}

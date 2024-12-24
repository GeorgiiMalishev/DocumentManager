package com.orgmange.document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Платёжка
 */
public class PaymentOrder extends Document {

    /**
     * Сумма
     */
    private final double amount;

    /**
     * Сотрудник
     */
    private final String employee;

    public PaymentOrder(String number,
                        LocalDate date,
                        String user,
                        double amount,
                        String employee) {
        super(number, date, user);
        this.amount = amount;
        this.employee = employee;
    }

    @Override
    public String getDisplayName() {
        return String.format("Платёжка от %s номер %s",
                date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), number);
    }

    @Override
    public String toFileFormat() {
        return String.format("PAYMENT|%s|%s|%s|%.2f|%s",
                number, date, user, amount, employee);
    }

    @Override
    public String getDisplayFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format("""
        Номер: %s
        Дата: %s
        Пользователь: %s
        Сумма: %.2f
        Сотрудник: %s""",
                number, date.format(formatter), user, amount, employee);
    }
}

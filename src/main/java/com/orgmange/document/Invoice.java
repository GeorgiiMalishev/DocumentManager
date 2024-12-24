package com.orgmange.document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Накладная
 */
public class Invoice extends Document {

    /**
     * Сумма
     */
    private final double amount;

    /**
     * Валюта
     */
    private final String currency;

    /**
     * Курс валюты
     */
    private final double exchangeRate;

    /**
     * Товар
     */
    private final String product;

    /**
     * Количество
     */
    private final double quantity;

    public Invoice(String number,
                   LocalDate date,
                   String user,
                   double amount,
                   String currency,
                   double exchangeRate,
                   String product,
                   double quantity) {
        super(number, date, user);
        this.amount = amount;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String getDisplayName() {
        return String.format("Накладная от %s номер %s",
                date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), number);
    }

    @Override
    public String toFileFormat() {
        return String.format("INVOICE|%s|%s|%s|%.2f|%s|%.2f|%s|%.2f",
                number, date, user, amount, currency, exchangeRate, product, quantity);
    }

    @Override
    public String getDisplayFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format("""
        Номер: %s
        Дата: %s
        Пользователь: %s
        Сумма: %.2f
        Валюта: %s
        Курс валюты: %.2f
        Товар: %s
        Количество: %.2f""",
                number, date.format(formatter), user, amount,
                currency, exchangeRate, product, quantity);
    }
}

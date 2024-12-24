package com.orgmange.document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Заявка на оплату
 */
public class PaymentRequest extends Document {

    /**
     * Контрагент
     */
    private final String contractor;

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
     * Комиссия
     */
    private final double commission;

    public PaymentRequest(String number, LocalDate date, String user, String contractor,
                          double amount, String currency, double exchangeRate, double commission) {
        super(number, date, user);
        this.contractor = contractor;
        this.amount = amount;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.commission = commission;
    }

    @Override
    public String getDisplayName() {
        return String.format("Заявка на оплату от %s номер %s",
                date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), number);
    }

    @Override
    public String toFileFormat() {
        return String.format("REQUEST|%s|%s|%s|%s|%.2f|%s|%.2f|%.2f",
                number, date, user, contractor, amount, currency, exchangeRate, commission);
    }

    @Override
    public String getDisplayFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return String.format("""
        Номер: %s
        Дата: %s
        Пользователь: %s
        Контрагент: %s
        Сумма: %.2f
        Валюта: %s
        Курс валюты: %.2f
        Комиссия: %.2f""",
                number, date.format(formatter), user, contractor,
                amount, currency, exchangeRate, commission);
    }
}

package com.orgmange.dialog;

import com.orgmange.view.DocumentCreationCallback;
import com.orgmange.document.Document;
import com.orgmange.document.PaymentRequest;

import javax.swing.*;

/**
 * Диалог создания заявки на оплату
 */
public class RequestDialog extends BaseDocumentDialog {
    public RequestDialog(JFrame parent, DocumentCreationCallback callback) {
        super(parent, "Новая заявка на оплату", callback);
        addField("Номер:", "number");
        addField("Дата:", "date");
        addField("Пользователь:", "user");
        addField("Контрагент:", "contractor");
        addField("Сумма:", "amount");
        addField("Валюта:", "currency");
        addField("Курс валюты:", "rate");
        addField("Комиссия:", "commission");
        setupButtons();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    protected Document createDocument(){
        return new PaymentRequest(
                fields.get("number").getText(),
                parseDate(fields.get("date").getText()),
                fields.get("user").getText(),
                fields.get("contractor").getText(),
                Double.parseDouble(fields.get("amount").getText()),
                fields.get("currency").getText(),
                Double.parseDouble(fields.get("rate").getText()),
                Double.parseDouble(fields.get("commission").getText())
        );
    }
}
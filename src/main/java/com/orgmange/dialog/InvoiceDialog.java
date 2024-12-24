package com.orgmange.dialog;

import com.orgmange.view.DocumentCreationCallback;
import com.orgmange.document.Document;
import com.orgmange.document.Invoice;

import javax.swing.*;

/**
 * Диалог создания накладной
 */
public class InvoiceDialog extends BaseDocumentDialog {
    public InvoiceDialog(JFrame parent, DocumentCreationCallback callback) {
        super(parent, "Новая накладная", callback);
        addField("Номер:", "number");
        addField("Дата:", "date");
        addField("Пользователь:", "user");
        addField("Сумма:", "amount");
        addField("Валюта:", "currency");
        addField("Курс валюты:", "rate");
        addField("Товар:", "product");
        addField("Количество:", "quantity");
        setupButtons();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    protected Document createDocument(){
        return new Invoice(
                fields.get("number").getText(),
                parseDate(fields.get("date").getText()),
                fields.get("user").getText(),
                Double.parseDouble(fields.get("amount").getText()),
                fields.get("currency").getText(),
                Double.parseDouble(fields.get("rate").getText()),
                fields.get("product").getText(),
                Double.parseDouble(fields.get("quantity").getText())
        );
    }
}
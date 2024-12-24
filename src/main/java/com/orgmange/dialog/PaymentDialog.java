package com.orgmange.dialog;

import com.orgmange.view.DocumentCreationCallback;
import com.orgmange.document.Document;
import com.orgmange.document.PaymentOrder;

import javax.swing.*;

/**
 * Диалог создания платёжки
 */
public class PaymentDialog extends BaseDocumentDialog {
    public PaymentDialog(JFrame parent, DocumentCreationCallback callback) {
        super(parent, "Новая платёжка", callback);
        addField("Номер:", "number");
        addField("Дата:", "date");
        addField("Пользователь:", "user");
        addField("Сумма:", "amount");
        addField("Сотрудник:", "employee");
        setupButtons();
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    protected Document createDocument(){
        return new PaymentOrder(
                fields.get("number").getText(),
                parseDate(fields.get("date").getText()),
                fields.get("user").getText(),
                Double.parseDouble(fields.get("amount").getText()),
                fields.get("employee").getText()
        );
    }
}
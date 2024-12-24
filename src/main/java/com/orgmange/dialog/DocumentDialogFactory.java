package com.orgmange.dialog;

import com.orgmange.view.DocumentCreationCallback;

import javax.swing.*;

/**
 * Фабрика диалогов создания документов
 */
public class DocumentDialogFactory {

    /**
     * Создаёт диалог создания документа
     * @param type тип документа
     * @param parent родительское окно
     * @param callback обратный вызов при создании документа
     * @return диалог создания документа
     */
    public static JDialog createDialog(String type, JFrame parent, DocumentCreationCallback callback) {
        return switch (type) {
            case "INVOICE" -> new InvoiceDialog(parent, callback);
            case "PAYMENT" -> new PaymentDialog(parent, callback);
            case "REQUEST" -> new RequestDialog(parent, callback);
            default -> throw new IllegalArgumentException("Unknown document type");
        };
    }
}
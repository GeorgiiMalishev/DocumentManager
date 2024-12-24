package com.orgmange.dialog;

import com.orgmange.view.DocumentCreationCallback;
import com.orgmange.document.Document;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Базовый класс для диалогов создания документов
 */
public abstract class BaseDocumentDialog extends JDialog {

    /**
     * Поля для ввода данных
     */
    protected final Map<String, JTextField> fields = new HashMap<>();

    /**
     * Обратный вызов при создании документа
     */
    protected final DocumentCreationCallback callback;

    protected BaseDocumentDialog(JFrame parent, String title, DocumentCreationCallback callback) {
        super(parent, title, true);
        this.callback = callback;
        setLayout(new GridLayout(0, 2, 5, 5));
    }

    /**
     * Добавляет поле ввода
     * @param label название поля
     * @param fieldName имя поля
     */
    protected void addField(String label, String fieldName) {
        add(new JLabel(label));
        JTextField field = new JTextField();
        fields.put(fieldName, field);
        add(field);
    }

    /**
     * Создаёт документ на основе введённых данных
     * @return документ
     * @throws Exception если данные введены некорректно
     */
    protected abstract Document createDocument() throws Exception;


    /**
     * Настраивает кнопки
     */
    protected void setupButtons() {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                Document doc = createDocument();
                callback.onDocumentCreated(doc);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка ввода данных");
            }
        });

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(e -> dispose());

        add(okButton);
        add(cancelButton);
    }

    protected LocalDate parseDate(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateStr, formatter);
    }
}
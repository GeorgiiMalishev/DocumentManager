package com.orgmange.dialog;

import com.orgmange.document.Document;

import javax.swing.*;
import java.awt.*;

/**
 * Диалог просмотра документа
 */
public class DocumentViewDialog extends JDialog {

    /**
     * Создаёт диалог просмотра документа
     * @param parent родительское окно
     * @param document документ
     */
    public DocumentViewDialog(JFrame parent, Document document) {
        super(parent, "Просмотр документа", true);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(document.getDisplayFormat());
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}
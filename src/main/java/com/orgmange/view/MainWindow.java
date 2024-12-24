package com.orgmange.view;

import com.orgmange.dialog.DocumentDialogFactory;
import com.orgmange.dialog.DocumentViewDialog;
import com.orgmange.document.Document;
import com.orgmange.file.DocumentFileHandler;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Главное окно приложения
 */
public class MainWindow extends JFrame {
    private final DocumentListPanel documentListPanel;
    private final DocumentFileHandler fileHandler;

    public MainWindow() {
        setTitle("Тест");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        documentListPanel = new DocumentListPanel();
        fileHandler = new DocumentFileHandler();

        add(documentListPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.EAST);
    }

    /**
     * Создаёт панель с кнопками
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        addButton(panel, "Накладная", () -> showDocumentDialog("INVOICE"));
        addButton(panel, "Платёжка", () -> showDocumentDialog("PAYMENT"));
        addButton(panel, "Заявка на оплату", () -> showDocumentDialog("REQUEST"));
        addButton(panel, "Сохранить", this::saveDocuments);
        addButton(panel, "Загрузить", this::loadDocuments);
        addButton(panel, "Просмотр", this::viewDocument);
        addButton(panel, "Удалить", documentListPanel::removeSelected);
        return panel;
    }

    /**
     * Добавляет кнопку на панель
     */
    private void addButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }

    /**
     * Показывает диалог создания документа
     */
    private void showDocumentDialog(String type) {
        DocumentDialogFactory.createDialog(type, this, documentListPanel::addDocument).setVisible(true);
    }

    /**
     * Сохраняет документы
     */
    private void saveDocuments() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                fileHandler.saveDocuments(documentListPanel.getAllDocuments(), chooser.getSelectedFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка при сохранении файла");
            }
        }
    }

    /**
     * Загружает документы
     */
    private void loadDocuments() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                List<Document> docs = fileHandler.loadDocuments(chooser.getSelectedFile());
                docs.forEach(documentListPanel::addDocument);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка при загрузке файла");
            }
        }
    }

    /**
     * Показывает диалог просмотра документа
     */
    private void viewDocument() {
        Document doc = documentListPanel.getSelectedDocument();
        if (doc == null) {
            JOptionPane.showMessageDialog(this, "Выберите документ для просмотра");
            return;
        }
        new DocumentViewDialog(this, doc).setVisible(true);
    }
}
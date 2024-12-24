package com.orgmange.view;

import com.orgmange.document.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Панель для отображения списка документов
 */
public class DocumentListPanel extends JPanel {
    private final DefaultListModel<DocumentListItem> documentListModel;
    private final JList<DocumentListItem> documentJList;

    public DocumentListPanel() {
        super(new BorderLayout());
        documentListModel = new DefaultListModel<>();
        documentJList = new JList<>(documentListModel);
        documentJList.setCellRenderer(new CheckboxListCellRenderer());
        documentJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = documentJList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    DocumentListItem item = documentListModel.getElementAt(index);
                    item.setSelected(!item.isSelected());
                    documentJList.repaint();
                }
            }
        });
        add(new JScrollPane(documentJList), BorderLayout.CENTER);
    }

    /**
     * Добавляет документ в список
     */
    public void addDocument(Document document) {
        if (document != null) {
            documentListModel.addElement(new DocumentListItem(document));
        }
    }

    /**
     * Возвращает список выбранных документов
     */
    public List<Document> getAllDocuments() {
        List<Document> docs = new ArrayList<>();
        for (int i = 0; i < documentListModel.size(); i++) {
            DocumentListItem item = documentListModel.getElementAt(i);
            if (item.isSelected()) {
                docs.add(item.getDocument());
            }
        }
        return docs;
    }

    /**
     * Возвращает выбранный документ
     */
    public Document getSelectedDocument() {
        DocumentListItem selected = documentJList.getSelectedValue();
        return selected != null ? selected.getDocument() : null;
    }

    /**
     * Удаляет выбранные элементы из списка документов
     */
    public void removeSelected() {
        DefaultListModel<DocumentListItem> model = (DefaultListModel<DocumentListItem>) documentJList.getModel();
        for (int i = model.getSize() - 1; i >= 0; i--) {
            if (model.getElementAt(i).isSelected()) {
                model.removeElementAt(i);
            }
        }
        documentJList.repaint();
    }
}
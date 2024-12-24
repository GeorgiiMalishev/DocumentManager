package com.orgmange.view;

import com.orgmange.document.Document;

/**
 * Элемент списка документов
 */
public class DocumentListItem {

    /**
     * Документ
     */
    private final Document document;

    /**
     * Выбран ли документ
     */
    private boolean selected;

    public DocumentListItem(Document document) {
        this.document = document;
        this.selected = false;
    }

    public String getDisplayName() {
        return document.getDisplayName();
    }

    public boolean isSelected() {
        return selected;
    }

    public Document getDocument() {
        return document;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
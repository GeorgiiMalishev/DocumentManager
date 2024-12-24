package com.orgmange.view;

import javax.swing.*;
import java.awt.*;

/**
 * Отображение элемента списка документов с чекбоксом
 */
public class CheckboxListCellRenderer
        extends JCheckBox
        implements ListCellRenderer<DocumentListItem> {

    /**
     * Получение компонента для отображения элемента списка
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends DocumentListItem> list,
                                                  DocumentListItem value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        setText(value.getDisplayName());
        setSelected(value.isSelected());
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        return this;
    }
}
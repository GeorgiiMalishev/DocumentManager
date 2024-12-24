package com.orgmange.document;

import java.time.LocalDate;

/**
 * Документ
 */
public abstract class Document {

    /**
     * Номер документа
     */
    protected String number;

    /**
     * Дата документа
     */
    protected LocalDate date;

    /**
     * Пользователь
     */
    protected String user;

    public Document(String number, LocalDate date, String user) {
        this.number = number;
        this.date = date;
        this.user = user;
    }

    /**
     * Возвращает отображение содержимого документа
     * @return формат содержимого документа
     */
    public abstract String getDisplayFormat();

    /**
     * Возвращает отображаемое имя документа
     * @return отображаемое имя
     */
    public abstract String getDisplayName();

    /**
     * Возвращает строку в файловом формате
     * @return строка в файловом формате
     */
    public abstract String toFileFormat();
}

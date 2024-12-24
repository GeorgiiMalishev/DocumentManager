package com.orgmange.file;

import com.orgmange.document.Document;
import com.orgmange.document.Invoice;
import com.orgmange.document.PaymentOrder;
import com.orgmange.document.PaymentRequest;

import java.time.LocalDate;

/**
 * Парсер документов
 */
public class DocumentParser {

    /**
     * Парсит строку в документ
     * @param line строка
     * @return документ
     */
    public static Document parseFromString(String line) {
        line = line.replace(',', '.');
        String[] parts = line.split("\\|");
        return switch (parts[0]) {
            case "INVOICE" -> new Invoice(
                    parts[1],
                    LocalDate.parse(parts[2]),
                    parts[3],
                    Double.parseDouble(parts[4]),
                    parts[5],
                    Double.parseDouble(parts[6]),
                    parts[7],
                    Double.parseDouble(parts[8])
            );
            case "PAYMENT" -> new PaymentOrder(
                    parts[1],
                    LocalDate.parse(parts[2]),
                    parts[3],
                    Double.parseDouble(parts[4]),
                    parts[5]
            );
            case "REQUEST" -> new PaymentRequest(
                    parts[1],
                    LocalDate.parse(parts[2]),
                    parts[3],
                    parts[4],
                    Double.parseDouble(parts[5]),
                    parts[6],
                    Double.parseDouble(parts[7]),
                    Double.parseDouble(parts[8])
            );
            default -> null;
        };
    }
}
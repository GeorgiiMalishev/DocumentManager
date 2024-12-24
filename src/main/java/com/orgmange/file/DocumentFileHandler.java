package com.orgmange.file;

import com.orgmange.document.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработчик файлов документов
 */
public class DocumentFileHandler {

    /**
     * Сохраняет документы в файл
     * @param documents список документов
     * @param file файл
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public void saveDocuments(List<Document> documents, File file) throws IOException {
        if (documents == null || documents.isEmpty()) {
            throw new IOException("Нет документов для сохранения");
        }

        if (!file.getName().toLowerCase().endsWith(".txt")) {
            file = new File(file.getPath() + ".txt");
        }
        
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Document doc : documents) {
                writer.println(doc.toFileFormat());
            }
        }
    }

    /**
     * Загружает документы из файла
     * @param file файл
     * @return список документов
     * @throws IOException если произошла ошибка ввода-вывода
     */
    public List<Document> loadDocuments(File file) throws IOException {
        List<Document> documents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Document doc = DocumentParser.parseFromString(line);
                if (doc != null) {
                    documents.add(doc);
                }
            }
        }
        return documents;
    }
}
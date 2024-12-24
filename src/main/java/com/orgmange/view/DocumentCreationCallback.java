package com.orgmange.view;

import com.orgmange.document.Document;

/**
 * Callback для создания документа
 */
@FunctionalInterface
public interface DocumentCreationCallback {
    void onDocumentCreated(Document document);
}
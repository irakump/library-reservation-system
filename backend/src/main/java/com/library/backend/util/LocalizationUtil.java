package com.library.backend.util;

import com.library.backend.book.Book;

public class LocalizationUtil {

    public static String resolve(String en, String ja, String ar, String lang) {
        return switch (lang) {
            case "ja" -> ja != null ? ja : en;
            case "ar" -> ar != null ? ar : en;
            default -> en;
        };
    }

    // Book specific helpers, so DTO's don't need to know the internal field names
    public static String getTitle(Book book, String lang) {
        return resolve(book.getTitle(), book.getTitle_ja(), book.getTitle_ar(), lang);
    }

    public static String getDescription(Book book, String lang) {
        return resolve(book.getDescription(), book.getDescription_ja(), book.getDescription_ar(), lang);
    }

}

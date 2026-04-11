package com.library.backend.util;

import com.library.backend.author.Author;
import com.library.backend.book.Book;
import com.library.backend.genre.Genre;
import com.library.backend.language.Language;

public class LocalizationUtil {

    public static String resolve(String en, String ja, String ar, String lang) {
        return switch (lang) {
            case "ja-JP" -> ja != null ? ja : en;
            case "ar-u-nu-arab" -> ar != null ? ar : en;
            default -> en;
        };
    }

    // Book specific helpers, so DTO's don't need to know the internal field names
    public static String getLocalizedTitle(Book book, String lang) {
        return resolve(book.getTitle(), book.getTitleJa(), book.getTitleAr(), lang);
    }

    public static String getLocalizedDescription(Book book, String lang) {
        return resolve(book.getDescription(), book.getDescriptionJa(), book.getDescriptionAr(), lang);
    }

    public static String getLocalizedGenre(Genre genre, String lang) {
        return resolve(genre.getGenre(), genre.getGenreJa(), genre.getGenreAr(), lang);
    }

    public static String getLocalizedLanguage(Language language, String lang) {
        return resolve(language.getLanguage(), language.getLanguageJa(), language.getLanguageAr(), lang);
    }

    public static String getLocalizedAuthorFirstName(Author author, String lang) {
        return resolve(author.getFirstName(), author.getFirstNameJa(), author.getFirstNameAr(), lang);
    }

    public static String getLocalizedAuthorLastName(Author author, String lang) {
        return resolve(author.getLastName(), author.getLastNameJa(), author.getLastNameAr(), lang);
    }

}

package com.library.backend.util;

import com.library.backend.author.Author;
import com.library.backend.book.Book;
import com.library.backend.genre.Genre;
import com.library.backend.language.Language;

/**
 * Utility class for resolving localized content
 * based on language code. Used across all DTOs
 * to avoid duplicate switch-cse logic.
 */
public final class LocalizationUtil {

    /** Private constructor to prevent instantiation. */
    private LocalizationUtil() { }

    /**
     * Resolves the correct string by language.
     * Falls back to English if the translation is null.
     *
     * @param english English value
     * @param japanese Japanese value
     * @param arabic Arabic value
     * @param lang the language code
     * @return the resolved localized string
     */
    public static String resolve(
            final String english,
            final String japanese,
            final String arabic,
            final String lang) {
        return switch (lang) {
            case "ja-JP" -> japanese != null ? japanese : english;
            case "ar-u-nu-arab" -> arabic != null ? arabic : english;
            default -> english;
        };
    }

    /**
     * Returns localized book title.
     *
     * @param book the book entity
     * @param lang the language code
     * @return localized title
     */
    public static String getLocalizedTitle(
           final Book book,
           final String lang) {
        return resolve(
                book.getTitle(),
                book.getTitleJa(),
                book.getTitleAr(),
                lang);
    }

    /**
     * Returns localized book description.
     *
     * @param book the book entity
     * @param lang the language code
     * @return localized description
     */
    public static String getLocalizedDescription(
          final Book book,
          final String lang) {
        return resolve(
                book.getDescription(),
                book.getDescriptionJa(),
                book.getDescriptionAr(),
                lang);
    }

    /**
     * Returns localized genre name.
     *
     * @param genre the genre entity
     * @param lang the language code
     * @return localized genre name
     */
    public static String getLocalizedGenre(
           final Genre genre,
           final String lang) {
        return resolve(
                genre.getGenre(),
                genre.getGenreJa(),
                genre.getGenreAr(),
                lang);
    }

    /**
     * Returns localized language name.
     *
     * @param language the language entity
     * @param lang the language code
     * @return localized language name
     */
    public static String getLocalizedLanguage(
           final Language language,
           final String lang) {
        return resolve(
                language.getLanguage(),
                language.getLanguageJa(),
                language.getLanguageAr(),
                lang);
    }

    /**
     * Returns localized author first name.
     *
     * @param author the author entity
     * @param lang the language code
     * @return localized author first name
     */
    public static String getLocalizedAuthorFirstName(
           final Author author,
           final String lang) {
        return resolve(
                author.getFirstName(),
                author.getFirstNameJa(),
                author.getFirstNameAr(),
                lang);
    }

    /**
     * Returns localized author last name.
     *
     * @param author the author entity
     * @param lang the language code
     * @return localized author last name
     */
    public static String getLocalizedAuthorLastName(
           final Author author,
           final String lang) {
        return resolve(
                author.getLastName(),
                author.getLastNameJa(),
                author.getLastNameAr(),
                lang);
    }

}

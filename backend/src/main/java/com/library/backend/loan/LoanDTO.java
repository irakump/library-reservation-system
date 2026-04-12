package com.library.backend.loan;

import com.library.backend.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** DTO representing data of loan and book entities returned to client*/
@Getter
@Setter
public class LoanDTO {

    /** Unique id of the loan*/
    private int loanId;

    /** Timestamp of creation time */
    private LocalDateTime createdAt;

    /** The date by which the book must be returned*/
    private LocalDate dueDate;

    /** The actual return day or null*/
    private LocalDateTime returnDate;

    /** ID of loaned book*/
    private String isbn;

    /** ID of user who executed loan*/
    private int userId;

    /** Title of loaned book*/
    private String title;

    /** Information of image file*/
    private String image;

    /** Summary of loaned book*/
    private String description;

    /** Year of publication of loaned book*/
    private int year;

    /** Language of loaned book*/
    private String language;

    /** Genre of loaned book*/
    private String genre;

    /**
     * Constructs a LoanDTO from a {@link Loan} entity.
     * Extracts relevant information from the loan and its associated book.
     *
     * @param loan the loan entity to convert
     */
    public LoanDTO(final Loan loan) {
        this.loanId = loan.getLoanId();
        this.createdAt = loan.getCreatedAt();
        this.dueDate = loan.getDueDate();
        this.returnDate = loan.getReturnDate();
        this.userId = loan.getUser().getUserId();
        this.isbn = loan.getBook().getIsbn();

        final Book book = loan.getBook();
        this.title = book.getTitle();
        this.image = book.getImage();
        this.description = book.getDescription();
        this.year = book.getYear();
        this.language = book.getLanguage();
        this.genre = book.getGenre();
    }
}

package com.library.backend.notifications;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    private final MailService mailService;


    public NotificationService(final MailService mailService) {
        this.mailService = mailService;
    }

    public void notifyOfNewLoan(final User user, final Book book) {
        final String subject = "New book loaned: " + book.getTitle();
        final String message =
                "Hello " + user.getNickname() + ", \n\nYou loaned book " + book.getTitle() + ".";
        mailService.sendMail(user.getEmail(), subject, message);
    }

    public void notifyDueDate(final User user, final Book book) {
        final String subject = "Book returned: " + book.getTitle();
        final String message = "Hey " + user.getNickname() + ", \n\nThe book " + book.getTitle() + " has been returned.";
        mailService.sendMail(user.getEmail(), subject, message);
    }

    public void notifyComingUpDueDate(final User user, final Book book) {
        final String subject = "Due date coming: " + book.getTitle();
        final String message = "Hello " + user.getNickname() + ",\n\nThe due date of " + book.getTitle() + " is coming in two days.";
        mailService.sendMail(user.getEmail(), subject, message);
    }

}

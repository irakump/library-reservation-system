package com.library.backend.notifications;


import com.library.backend.book.Book;
import com.library.backend.loan.Loan;
import com.library.backend.loan.LoanRepository;
import com.library.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {
    private final MailService mailService;

    @Autowired
    private LoanRepository loanRepository;

    public NotificationService(final MailService mailService) {
        this.mailService = mailService;
    }

    public void notifyOfNewLoan(final User user, final Book book) {
        final String subject = "New book loaned: " + book.getTitle();
        final String message =
                "Hello " + user.getNickname() + ", \n\n You loaned book " + book.getTitle() + ".";
        mailService.sendMail(user.getEmail(), subject, message);
    }

    @Scheduled(cron = "0 0 20 * * *")
    public void notifyAllDueDates() {
        final LocalDate today = LocalDate.now();
        final List<Loan> dueLoans = loanRepository.findByDueDate(today);

        for (final Loan loan : dueLoans) {
            notifyDueDate(loan.getUser(), loan.getBook());
        }
    }

    public void notifyDueDate(final User user, final Book book) {
        final String subject = "Book returned: " + book.getTitle();
        final String message = "Hello " + user.getNickname() + ", \n\n The book " + book.getTitle() + " has been returned.";
        mailService.sendMail(user.getEmail(), subject, message);
    }

    @Scheduled(cron = "0 0 20 * * *")
    public void notifyBeforeDueDate() {
        final LocalDate notifyDay = LocalDate.now().minusDays(2);
        final List<Loan> loans = loanRepository.findByDueDate(notifyDay);

        for (final Loan loan : loans) {
            notifyDueDate(loan.getUser(), loan.getBook());
        }
    }
}

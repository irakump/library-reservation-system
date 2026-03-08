package com.library.backend.notifications;


import com.library.backend.book.Book;
import com.library.backend.loan.Loan;
import com.library.backend.loan.LoanRepository;
import com.library.backend.loan.LoanService;
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

    public NotificationService(MailService mailService) {
        this.mailService = mailService;
    }

    public void notifyOfNewLoan(User user, Book book) {
        String subject = "New book loaned: " + book.getTitle();
        String message =
                "Hello " + user.getNickname() + ", \n\n You loaned book " + book.getTitle() + ". Lisää duedate";
        mailService.sendMail(user.getEmail(), subject, message);
    }

    @Scheduled(cron = "0 0 19 * * *")
    public void notifyAllDueDates() {
        LocalDate today = LocalDate.now();
        List<Loan> dueLoans = loanRepository.findByDueDate(today);

        for (Loan loan : dueLoans) {
            notifyDueDate(loan.getUser(), loan.getBook());
        }
    }

    public void notifyDueDate(User user, Book book) {
        String subject = "Book returned: " + book.getTitle();
        String message = "Hello " + user.getNickname() + ", \n\n The book " + book.getTitle() + " has been returned.";
        mailService.sendMail(user.getEmail(), subject, message);
    }

    @Scheduled(cron = "0 0 19 * * *")
    public void notifyBeforeDueDate() {
        LocalDate notifyDay = LocalDate.now().minusDays(2);
        List<Loan> loans = loanRepository.findByDueDate(notifyDay);

        for (Loan loan : loans) {
            notifyDueDate(loan.getUser(), loan.getBook());
        }
    }
}

package com.library.backend.notifications;

import com.library.backend.book.Book;
import com.library.backend.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private MailService mailService; //mocks mailService so no real mails are sent

    @InjectMocks
    private NotificationService notificationService;

    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        user = mock(User.class); //creates fake user object
        when(user.getNickname()).thenReturn("User"); //mocks getNickname method used by notificationService
        when(user.getEmail()).thenReturn("test@gmail.com"); //mocks getEmail method

        book = mock(Book.class); //creates fake book objects
        when(book.getTitle()).thenReturn("Foundation"); //mocks getTitle
    }

    //tests for notifyOfNewLoan

    //verifies that notification is sent to correct mail
    @Test
    void shouldSendToCorrectEmail() {
        notificationService.notifyOfNewLoan(user, book);

        verify(mailService).sendMail(
                eq("test@gmail.com"), //eq is mockito matcher (same as equals in java)
                anyString(), //any string since only the subject is tested
                anyString()
        );
    }

    //verifies that notification is sent with correct subject text
    @Test
    void shouldSendWithCorrectSubject() {
        notificationService.notifyOfNewLoan(user, book);

        verify(mailService).sendMail(
                anyString(),
                eq("New book loaned: Foundation"), //only subject is tested
                anyString());
    }

    //verifies that notification is sent with correct message
    @Test
    void shouldSendWithCorrectMessage() {
        notificationService.notifyOfNewLoan(user, book);

        verify(mailService).sendMail(
                anyString(),
                anyString(),
                eq("Hello User, \n\nYou loaned book Foundation.") //only message is tested
        );
    }

    //verifies if correct mail is sent when book is returned
    @Test
    void shouldNotifyDueDate() {
        notificationService.notifyDueDate(user, book);

        verify(mailService).sendMail(
                "test@gmail.com",
                "Book returned: Foundation",
                "Hey User, \n\nThe book Foundation has been returned."
        );
    }

    //verifies if correct mail of upcoming due date is sent
    @Test
    void shouldNotifyComingDueDate() {
        notificationService.notifyComingUpDueDate(user, book);

        verify(mailService).sendMail(
                "test@gmail.com",
                "Due date coming: Foundation",
                "Hello User,\n\nThe due date of Foundation is coming in two days."
        );

    }

}






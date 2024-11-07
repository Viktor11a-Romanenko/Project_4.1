package test;

import model.Book;
import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;
    @BeforeEach
    void setUp() {
        user = new User( "mail@gmail.com", "1234");
    }

    @Test
    void getPassword() {
        assertEquals("1234", user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("newPassword123");
        assertEquals("newPassword123",user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("mail@gmail.com", user.getEmail());
    }

    @Test
    void testToString() {
        assertEquals("User{email='mail@gmail.com', role=USER}", user.toString());
    }

    @Test
    void getRole() {
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void setRole() {
        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void getUserBooks() {
        assertEquals(0, user.getUserBooks().size()); // initially no books taken
        Book book = new Book(1, "Толстой", "Война и мир");

        user.takeBookHome(book);
        assertEquals(1, user.getUserBooks().size()); // initially no books taken

    }

    @Test
    void setEmail() {
        user.setEmail("ang@gmail.com");
        assertEquals("ang@gmail.com", user.getEmail());
    }



    @Test
    void returnBook() {
        assertEquals(0, user.getUserBooks().size()); // initially no books taken
        Book book = new Book(1, "Толстой", "Война и мир");

        user.takeBookHome(book);
        assertEquals(1, user.getUserBooks().size()); // initially no books taken

        user.returnBook(book);
        assertEquals(0, user.getUserBooks().size()); // initially no books taken

    }
}
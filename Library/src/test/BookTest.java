package test;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BookTest {
    Book book;
    @BeforeEach
    void setUp() {
        book = new Book(1, "Otfried Preussler", "Das Kleine Gespenst");
    }

    @Test
    void getIdBook() {
        assertEquals(1, book.getIdBook());
    }

    @Test
    void setIdBook() {
        book.setIdBook(2);

        assertEquals(2, book.getIdBook());
    }

    @Test
    void getAuthorBook() {
        assertEquals("Otfried Preussler", book.getAuthorBook());
    }

    @Test
    void setAuthorBook() {
        String newAuthorBook = "George Ourel";
        book.setAuthorBook(newAuthorBook);
        assertEquals(newAuthorBook, book.getAuthorBook());
    }

    @Test
    void getNameBook() {
        assertEquals("Das Kleine Gespenst", book.getNameBook());
    }

    @Test
    void setNameBook() {
        String newNameBook = "Остров Сокровищ";
        book.setNameBook(newNameBook);
        assertEquals(newNameBook, book.getNameBook());
    }

    @Test
    void isBusy() {
        assertFalse(book.isBusy());
    }

    @Test
    void setBusy() {
        book.setBusy(true);
        assertTrue(book.isBusy());
    }

    @Test
    void testEquals() {
        Book newBook = new Book(1, book.getAuthorBook(), book.getNameBook());
        assertTrue(book.equals(newBook));
    }

    @Test
    void testToString() {
        String resultString = "Book{idBook='1', authorBook='Otfried Preussler', nameBook='Das Kleine Gespenst', isBusy=false}";
        assertEquals(resultString, book.toString());
    }
}
package test;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.*;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;



class BookRepositoryImplTest {

    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository = new BookRepositoryImpl();
    }

    @Test
    public void testConstructor(){
        assertEquals(bookRepository.getAllBooks().size(), 8);
    }

    @Test
    public void testAddBook() {
        // Arrange
        String authorBook = "Александр Пушкин";
        String nameBook = "Капитанская Дочка";

        // Act
        Book addedBook = bookRepository.addBook(authorBook, nameBook);

        // Assert
        assertNotNull(addedBook);
        assertEquals(authorBook, addedBook.getAuthorBook());
        assertEquals(nameBook, addedBook.getNameBook());
        assertEquals(bookRepository.getAllBooks().size(), 9); // Check total book count
    }

    @Test
    public void testGetAllBooks() {
        // Act
        MyList<Book> allBooks = bookRepository.getAllBooks();

        // Assert
        assertNotNull(allBooks);
        assertEquals(8, allBooks.size()); // Pre-populated books in addBooks()
    }

    @Test
    public void testGetById_ExistingId() {
        // Arrange
        int existingIdBook = 1;

        // Act
        Book book = bookRepository.getById(existingIdBook);

        // Assert
        assertNotNull(book);
        assertEquals(existingIdBook, book.getIdBook());
        assertEquals("Александр Пушкин", book.getAuthorBook());
        assertEquals("Евгений Онегин", book.getNameBook());
    }

    @Test
    public void testGetById_NonexistentId() {
        // Arrange
        int nonExistentIdBook = 100;

        // Act
        Book book = bookRepository.getById(nonExistentIdBook);

        // Assert
        assertNull(book);
    }

    @Test
    public void testGetByName_ExistingName() {
        // Arrange
        String name = "Война";

        // Act
        MyList<Book> books = bookRepository.getByName(name);

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size()); // Only "Война и мир"
        assertEquals("Лев Толстой", books.get(0).getAuthorBook());
        assertEquals("Война и мир", books.get(0).getNameBook());
        assertTrue(books.get(0).getNameBook().contains(name));
    }

    @Test
    public void testGetByName_NonexistentName() {
        // Arrange
        String nonExistentNameBook = "Карлсон";

        // Act
        MyList<Book> books = bookRepository.getByName(nonExistentNameBook);

        // Assert
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

    @Test
    public void testGetByAuthor_ExistingAuthor() {
        // Arrange
        String authorBook = "Лев Толстой";

        // Act
        MyList<Book> books = bookRepository.getByAuthor(authorBook);

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size()); // Only "Война и мир"
        assertTrue(books.get(0).getAuthorBook().contains(authorBook));
    }

    @Test
    public void testGetByAuthor_NonexistentAuthor() {
        // Arrange
        String nonExistentAuthorBook = "Дейл Карнеги";

        // Act
        MyList<Book> books = bookRepository.getByAuthor(nonExistentAuthorBook);

        // Assert
        assertNotNull(books);
        assertTrue(books.isEmpty());
    }

    // Add similar tests for getFreeBooks() and getBusyBooks() methods based on their logic.

    @Test
    public void testDeleteBook() {
        // Arrange
        Book bookToDelete = bookRepository.getById(5); // Assuming the first book is not busy

        // Act
        bookRepository.deleteBook(bookToDelete);

        // Assert
        assertEquals(7, bookRepository.getAllBooks().size()); // One book less
        assertNull(bookRepository.getById(5));
    }
}
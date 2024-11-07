package repository;

import model.Book;
import utils.MyList;

public interface BookRepository {
    Book addBook(String authorBook, String nameBook);

    MyList<Book> getAllBooks();

    Book getById(int idBook);

    MyList<Book> getByName(String nameBook);

    MyList<Book> getByAuthor(String authorBook);

    MyList<Book> getFreeBooks();

    MyList<Book> getBusyBooks();

    void deleteBook(Book book);

}

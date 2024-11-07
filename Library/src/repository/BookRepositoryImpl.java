package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    private final MyList<Book> books;
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<Book>() {
            @Override
            public boolean remove() {
                return false;
            }
        };
        addBooks();
    }

    private void addBooks() {
        books.addAll(
                new Book(1, "Александр Пушкин", "Евгений Онегин"),
                new Book(2, "Юрий Лермонтов", "Бородино"),
                new Book(3, "Лев Толстой", "Война и мир"),
                new Book(4, "Михаил Булгаков", "Собачье сердце"),
                new Book(5, "Марина Цветаева", "Сборник стихов"),
                new Book(6, "Джейми Оливер", "Сборник лучших блюд английской кухни"),
                new Book(7, "Алексей Толстой", "Петр 1"),
                new Book(8, "Михаил Шолохов", "Тихий Дон")
        );
    }

    @Override
    public Book addBook(String authorBook, String nameBook) {
        Book book = new Book(1, authorBook, nameBook);
        books.add(book);
        return book;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getById(int idBook) {
        for (Book book : books) {
            if (book.getIdBook() == idBook) return book;
        }
        return null;
    }

    @Override
    public MyList<Book> getByName(String nameBook) {
        MyList<Book> result = new MyArrayList<Book>() {
            @Override
            public boolean remove() {
                return false;
            }
        };
        for (Book book : books) {
            if (book.getNameBook().contains(nameBook)) result.add(book);
        }
        return result;
    }

    @Override
    public MyList<Book> getByAuthor(String authorBook) {
        MyList<Book> result = new MyArrayList<Book>() {
            @Override
            public boolean remove() {
                return false;
            }
        };
        for (Book book : books) {
            if (book.getAuthorBook().contains(authorBook)) result.add(book);
        }
        return result;
    }

    @Override
    public MyList<Book> getFreeBooks() {
        MyList<Book> result = new MyArrayList<Book>() {
            @Override
            public boolean remove() {
                return false;
            }
        };

        for (Book book : books) {
            if (!book.isBusy()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getBusyBooks() {
        MyList<Book> result = new MyArrayList<Book>() {
            @Override
            public boolean remove() {
                return false;
            }
        };

        for (Book book : books) {
            if (book.isBusy()) result.add(book);
        }
        return result;
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

}

package service;

import model.Book;
import model.User;
import utils.MyList;

public interface MainService {

    //Creat
    Book addBook (String nameBook, String authorBook);

    //Read
    MyList<Book> getAllBooks();

    MyList<Book> getByName (String nameBook);

    MyList<Book> getByAuthor (String authorName);

    MyList<Book> getFreeBooks();
    MyList<Book> getBusyBooks();




    //Update

    boolean updateEditBook(String nameBook, String authorName, int idBook);

    boolean takeBook(int idBook);

    boolean returnBook(int idBook);

    //Delet

    Book deleteBook (int idBook);

    User registerUser(String email, String password);
    boolean loginUser (String email, String password);
    void logout();








}

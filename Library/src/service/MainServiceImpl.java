package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;
import utils.PersonValidator;

public class MainServiceImpl implements MainService {

    private final BookRepository repositoryBook;
    private final UserRepository repositoryUser;
    private User activeUser;


    public MainServiceImpl(BookRepository repositoryBook, UserRepository repositoryUser) {
        this.repositoryBook = repositoryBook;
        this.repositoryUser = repositoryUser;
    }

    @Override
    public Book addBook(String nameBook, String authorBook) {
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Добавление новой книги невозможно.");
        }

        if (nameBook == null || authorBook == null) {

            System.out.println("Добавление книги невозможно");
        }
        Book book = repositoryBook.addBook(authorBook, nameBook);


        return book;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return repositoryBook.getAllBooks();
    }


    @Override
    public MyList<Book> getByName(String nameBook) {
        if (nameBook == null || nameBook.isEmpty()) {
            System.out.println("Ошибка: название книги не может быть пустым.\"");
            return null;
        }
        return repositoryBook.getByName(nameBook);
    }

    @Override
    public MyList<Book> getByAuthor(String authorName) {
        if (authorName == null || authorName.isEmpty()){
            System.out.println("Ошибка: имя автора не может быть пустым.");
            return null;
        }
        return repositoryBook.getByAuthor(authorName);

    }

    @Override
    public MyList<Book> getFreeBooks() {
        return repositoryBook.getFreeBooks();
    }

    @Override
    public MyList<Book> getBusyBooks() {
        return repositoryBook.getBusyBooks();
    }




    @Override
    public boolean updateEditBook(String nameBook, String authorName, int idBook) {
        if (activeUser == null || activeUser.getRole() != Role.ADMIN){
            System.out.println("Изменить книгу может только администратор");
            return false;
        }

        Book book = repositoryBook.getById(idBook);
        if (book == null) return  false;

        if (nameBook == null || nameBook.isEmpty()){
            return false;
        }
        book.setNameBook(nameBook);

        if (authorName == null || authorName.isEmpty()){
           return false;
        }
        book.setAuthorBook(authorName);
        return  true;

    }

    @Override
    public boolean takeBook(int idBook) {
        if (activeUser == null){
            System.out.println("Выполните вход в систему.");
            return false;
        }

        Book book = repositoryBook.getById(idBook);
        if (book == null) return false;
        if (!book.isBusy()){
            System.out.println("Книга уже занята.");
            return false;
        }
        book.setBusy(true);
        activeUser.takeBookHome(book);
        System.out.println("Книга успешно взята.");
        return true;
    }

    @Override
    public boolean returnBook(int idBook) {
        if (activeUser == null){
            System.out.println("Выполните вход в систему.");
            return false;
        }

        Book book = repositoryBook.getById(idBook);
        if (book == null) return false;
        if (!book.isBusy()){
            System.out.println("Книга уже свободна.");
            return false;
        }
        if (!activeUser.getUserBooks().contains(book)){
            System.out.println();
            return false;
        }
        book.setBusy(false);
        activeUser.returnBook(book);
        System.out.println("Книга успешно возвращена.");
        return true;
    }

    @Override
    public Book deleteBook(int idBook) {
        if (activeUser == null || activeUser.getRole() != Role.ADMIN){
            System.out.println("Доступно только Администратору");
            return null;
        }
        Book book = repositoryBook.getById(idBook);
        if (book == null) return null;

        repositoryBook.deleteBook(book);
        return book;

    }

    @Override
    public User registerUser(String email, String password) {
        if (!PersonValidator.isEmailValid(email)){
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!PersonValidator.isPasswordValid(password)){
            System.out.println("Password не прошел проверку!");
            return null;
        }

        if (!PersonValidator.isPasswordValid(password)){
            System.out.println("Email уже используется!");
            return null;
        }

        User user = repositoryUser.addUser(email,password);
        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User user = repositoryUser.getUserByEmail(email);
        if (user == null){
            System.out.println("Неверный email или password.");
            return false;
        }
        if (!user.getPassword().equals(password)){
            System.out.println("Неверный email или password.");
            return false;
        }

        activeUser = user;
        return true;
    }

    @Override
    public void logout() {
        activeUser = null;
    }
}

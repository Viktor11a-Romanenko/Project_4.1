package view;

import model.Book;
import model.User;
import service.MainService;
import utils.MyList;

import java.util.Scanner;

public class Menu {
        private final MainService service;
        private final Scanner scanner = new Scanner(System.in);
        private User currentUser; // Пользователь, который вошел в систему

        public Menu(MainService service) {
            this.service = service;
        }

        public void run() {
            while (true) {
                if (currentUser == null) {
                    showLoginMenu();
                } else {
                    showMainMenu();
                }
            }
        }

        private void showLoginMenu() {
            System.out.println("\nWelcome to the library system");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        private void showMainMenu() {
            System.out.println("\nLibrary Menu");
            System.out.println("1. List all books");
            System.out.println("2. List my books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. View book details");
            System.out.println("6. Edit book details");
            System.out.println("7. List all free books");
            System.out.println("8. List all borrowed books");
            System.out.println("9. View who has a book");
            System.out.println("0. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    listMyBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    viewBookDetails();
                    break;
                case 6:
                    editBookDetails();
                    break;
                case 7:
                    listFreeBooks();
                    break;
                case 8:
                    listBorrowedBooks();
                    break;
                case 9:
                    viewWhoHasBook();
                    break;
                case 0:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        private void login() {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = service.registerUser(username, password);
            if (user != null) {
                currentUser = user;
                System.out.println("Welcome, " + user.getRole() + "!");
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }

        private void register() {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter role (USER/ADMIN): ");
            String role = scanner.nextLine();

            User user = service.registerUser(username, password);
            if (user != null) {
                currentUser = user;
                System.out.println("Registration successful. Welcome, " + user.getRole() + "!");
            } else {
                System.out.println("Registration failed. Try again.");
            }
        }

        private void logout() {
            currentUser = null;
            System.out.println("You have logged out.");
        }

        private void listAllBooks() {
            MyList<Book> books = service.getAllBooks();
            printBookList(books);
        }

    private void printBookList(MyList<Book> books) {
    }

    private void listMyBooks() {
            MyList<Book> books = service.getAllBooks();
            printBookList(books);
        }

        private void borrowBook() {
            MyList<Book> books = service.getFreeBooks();
            printBookList(books);
            System.out.print("Choose a book number to borrow: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (service.takeBook(choice - 1)) {
                System.out.println("You borrowed the book.");
            } else {
                System.out.println("Can't borrow the book. It's already taken.");
            }
        }

        private void returnBook() {
            boolean books = service.returnBook(1);
            printBookList(books);
            System.out.print("Choose a book number to return: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            if (!service.returnBook(choice - 1)) {
                System.out.println("Can't return the book.");
            } else {
                System.out.println("You returned the book.");
            }
        }

        private void viewBookDetails() {
            listAllBooks();
            System.out.print("Choose a book number to view details: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            Book book = service.getAllBooks().get(choice - 1);
            System.out.println("Details of the book:");
            System.out.println("Title: " + book.getNameBook());
            System.out.println("Author: " + book.getAuthorBook());
            // Добавь больше деталей, если нужно
        }

        private void editBookDetails() {
            if (!currentUser.getRole().equals("ADMIN")) {
                System.out.println("You do not have permission to edit book details.");
                return;
            }

            listAllBooks();
            System.out.print("Choose a book number to edit: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            Book book = service.getAllBooks().get(choice - 1);

            System.out.print("Enter new title (or press Enter to keep current): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                book.setNameBook(newTitle);
            }

            System.out.print("Enter new author (or press Enter to keep current): ");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isEmpty()) {
                book.setAuthorBook(newAuthor);
            }

            System.out.println("Book details updated!");
        }

        private void listFreeBooks() {
            MyList<Book> books = service.getFreeBooks();
            printBookList(books.isEmpty());
        }

    private void printBookList(boolean empty) {
    }

    private void listBorrowedBooks() {
            MyList<Book> books = service.getBusyBooks();
            printBookList(books.remove());
        }

        private void viewWhoHasBook() {
            listAllBooks();
            System.out.print("Choose a book number to view who has it: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            User user = (User) service.getBusyBooks();
            if (user != null) {
                System.out.println("The book is borrowed by: " + user.getRole());
            } else {
                System.out.println("The book is currently available.");
            }
        }





}





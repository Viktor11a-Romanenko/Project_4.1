package model;

import java.util.Objects;

public class Book {
    private int idBook;
    private String authorBook;
    private String nameBook;
    private boolean isBusy;



    public Book( int idBook,String authorBook, String nameBook) {
        this.idBook = idBook;
        this.authorBook = authorBook;
        this.nameBook = nameBook;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        if (this.authorBook == book.authorBook && this.nameBook == book.nameBook)  return true;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isBusy);
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook='" + idBook + '\'' +
                ", authorBook='" + authorBook + '\'' +
                ", nameBook='" + nameBook + '\'' +
                ", isBusy=" + isBusy +
                '}';
    }
}

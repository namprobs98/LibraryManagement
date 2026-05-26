package Java.LibraryManagement.models;

public class Book {
    private String book_id;
    private String book_title;
    private String book_author;
    private String book_genre;
    private int book_copies;
    private int book_borrowed;

    public Book(String book_id, String book_title, String book_author, String book_genre, int book_copies) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_genre = book_genre;
        this.book_copies = book_copies;
        this.book_borrowed = 0; // Initially, no copies are borrowed
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_genre() {
        return book_genre;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    public int getBook_copies() {
        return book_copies;
    }

    public void setBook_copies(int book_copies) {
        this.book_copies = book_copies;
    }   

    public int getBook_borrowed() {
        return book_borrowed;
    }

    public void setBook_borrowed(int book_borrowed) {
        this.book_borrowed = book_borrowed;
    }
}

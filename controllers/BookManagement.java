

package Java.LibraryManagement.controllers;

import Java.LibraryManagement.models.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookManagement {

    Scanner sc = new Scanner(System.in);
    private Map<String, Book> books;

    public BookManagement( Map<String, Book> books) {
        this.books = books;
    }

    public void manageBooks() {
        // Code to manage library operations
        System.out.println();
        System.out.println("Library Management System");
        System.out.println("___________________________________________________");
        System.out.println("1. Adding a book");
        System.out.println("2. Viewing book details");
        System.out.println("3. Updating book information");
        System.out.println("4. Deleting a book");
        System.out.println("5. Searching for a book");
        System.out.println("6. Displaying a book list");
        System.out.println("7. Exit");
        // get user input
        // switch case for options
        int choice = 0;
        while (choice != 7) {
            System.out.print("Enter your choice: ");
            String choiceStr = sc.nextLine();
            while (!isValidInt(choiceStr, 1, 7)) {
                System.out.print("Invalid input. Please enter a number between 1 and 7: ");
                choiceStr = sc.nextLine();
            }
            choice = Integer.parseInt(choiceStr);
            switch (choice) {
                case 1:
                    // Code to add a book
                    addBook();
                    break;
                case 2:
                    // Code to view book details
                    viewBookDetails();
                    break;
                case 3:
                    // Code to update book information
                    updateBookInformation();
                    break;
                case 4:
                    // Code to delete a book
                    deleteBook();
                    break;
                case 5:
                    // Code to search for a book
                    searchBook();
                    break;
                case 6:
                    // Code to display a book list
                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        System.out.println("Book List:");
                        for (Book book : books.values()) {
                            System.out.println("ID: " + book.getBook_id() + ", Title: " + book.getBook_title());
                        }
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void addBook() {
        // Code to add a book
        System.out.print("Enter book ID: ");
        String book_id = sc.nextLine();
        // Check if book ID already exists
        if (books.containsKey(book_id)) {
            System.out.println("Book ID already exists. Please try again.");
            return;
        }

        System.out.print("Enter book title: ");
        String book_title = sc.nextLine();
        System.out.print("Enter book author: ");
        String book_author = sc.nextLine();
        System.out.print("Enter book genre: ");
        String book_genre = sc.nextLine();
        System.out.print("Enter number of copies: ");
        String copiesStr = sc.nextLine();
        while (!isValidInt(copiesStr, 1, Integer.MAX_VALUE)) {
            System.out.print("Invalid input. Please enter a positive integer for number of copies: ");
            copiesStr = sc.nextLine();
        }
        int book_copies = Integer.parseInt(copiesStr);
        Book book = new Book(book_id, book_title, book_author, book_genre, book_copies);
        books.put(book_id, book);
        System.out.println("Book added successfully.");
    }

    public void viewBookDetails() {
        // Code to view book details
        System.out.print("Enter book ID to view details: ");
        String book_id = sc.nextLine();
        Book book = books.get(book_id);
        if (book != null) {
            System.out.println("Book ID: " + book.getBook_id());
            System.out.println("Title: " + book.getBook_title());
            System.out.println("Author: " + book.getBook_author());
            System.out.println("Genre: " + book.getBook_genre());
            System.out.println("Copies Available: " + book.getBook_copies());
        } else {
            System.out.println("Book not found.");
        }
    }

    public void updateBookInformation() {
        // Code to update book information
        System.out.print("Enter book ID to update: ");
        String book_id = sc.nextLine();
        Book book = books.get(book_id);
        if (book != null) {
            System.out.print("Enter new title: ");
            String book_title = sc.nextLine();
            System.out.print("Enter new author: ");
            String book_author = sc.nextLine();
            System.out.print("Enter new genre: ");
            String book_genre = sc.nextLine();
            System.out.print("Enter new number of copies: ");
            String copiesStr = sc.nextLine();
            while (!isValidInt(copiesStr, 1, Integer.MAX_VALUE)) {
                System.out.print("Invalid input. Please enter a positive integer for number of copies: ");
                copiesStr = sc.nextLine();
            }
            int book_copies = Integer.parseInt(copiesStr);
            book.setBook_title(book_title);
            book.setBook_author(book_author);
            book.setBook_genre(book_genre);
            book.setBook_copies(book_copies);
            System.out.println("Book information updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }
    public void deleteBook() {
        // Code to delete a book
        System.out.print("Enter book ID to delete: ");
        String book_id = sc.nextLine();
        if (books.containsKey(book_id)) {
            books.remove(book_id);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void searchBook() {
        // Code to search for a book
        System.out.println("1. Searching by book_id");
        System.out.println("2. Searching by book_title");
        String searchChoiceStr = sc.nextLine();
        while (!isValidInt(searchChoiceStr, 1, 2)) {
            System.out.print("Invalid input. Please enter 1 or 2: ");
            searchChoiceStr = sc.nextLine();
        }
        int searchChoice = Integer.parseInt(searchChoiceStr);
        switch (searchChoice) {
            case 1:
                System.out.print("Enter book ID to search: ");
                String book_id = sc.nextLine();
                if (books.containsKey(book_id)) {
                    Book book = books.get(book_id);
                    System.out.println("Book found: " + book.getBook_title());
                } else {
                    System.out.println("Book not found.");
                }
                break;
            case 2:
                System.out.print("Enter book title to search: ");
                String book_title = sc.nextLine();
                boolean found = false;
                for (Book book : books.values()) {
                    if (book.getBook_title().equalsIgnoreCase(book_title)) {
                        System.out.println("Book found: " + book.getBook_id());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Book not found.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Validate if input is integer and in range [min, max]
    private boolean isValidInt(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
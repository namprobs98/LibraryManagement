package Java.LibraryManagement.controllers;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Java.LibraryManagement.models.Book;
import Java.LibraryManagement.models.Member;
import Java.LibraryManagement.models.Borrow;

public class BorrowingManagement {
    private Map<String, Book> books;
    private Map<String, Member> members;
    private List<Borrow> borrowingRecords;

    Scanner sc = new Scanner(System.in);

    public BorrowingManagement(Map<String, Book> books, Map<String, Member> members, List<Borrow> borrowingRecords) {
        this.books = books;
        this.members = members;
        this.borrowingRecords = borrowingRecords;
    }

    public void manageBorrowing() {
        // Code to manage borrowing operations
        System.out.println();
        System.out.println("Borrowing Management System");
        System.out.println("______________________________");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Borrowing Records");
            System.out.println("4. Exit");
        // get user input
        // switch case for options
        int choice = 0;
        while (choice != 4) {
            System.out.print("Enter your choice: ");
            String choiceStr = sc.nextLine();
            while (!isValidInt(choiceStr, 1, 4)) {
                System.out.print("Invalid input. Please enter a number between 1 and 4: ");
                choiceStr = sc.nextLine();
            }
            choice = Integer.parseInt(choiceStr);
            switch (choice) {
                case 1:
                    // Code to borrow a book
                    borrowBook();
                    break;
                case 2:
                    // Code to return a book
                    returnBook();
                    break;
                case 3:
                    // Code to view borrowing records
                    viewBorrowingRecords();
                    break;
                case 4:
                    System.out.println("Exiting Borrowing Management. Returning to main menu.");
                    break;
            }
        }
    }

    //Borrow book
    public void borrowBook() {
        // Code to borrow a book
        System.out.println("Borrowing a book...");
        System.out.println("Enter member ID: ");
        String memberId = sc.nextLine();
        // Check if member exists
        if (!members.containsKey(memberId)) {
            System.out.println("Member ID not found. Please try again.");
            return;
        }
        System.out.println("Enter book ID: ");
        String bookId = sc.nextLine();
        // Check if book exists
        if (!books.containsKey(bookId)) {
            System.out.println("Book ID not found. Please try again.");
            return;
        }
        // Kiểm tra số lượng bản đã cho mượn có < số lượng bản có sẵn không
        Book book = books.get(bookId);
        if (book.getBook_borrowed() >= book.getBook_copies()) {
            System.out.println("Sorry, all copies of this book are currently borrowed.");
            return;
        }
        // Update the book's borrowed count and record the borrowing transaction       
        book.setBook_borrowed(book.getBook_borrowed() + 1);
        String borrowId = String.valueOf(borrowingRecords.size() + 1);
        String borrowDate = java.time.LocalDate.now().toString();
        Borrow borrow = new Borrow(borrowId, memberId, bookId, borrowDate, null);
        borrowingRecords.add(borrow);
        System.out.println("Book borrowed successfully.");
    }

    //Return book
    public void returnBook() {
        // Code to return a book
        System.out.println("Returning a book...");
        System.out.println("Enter member ID: ");
        String memberId = sc.nextLine();
        // Check if member exists
        if (!members.containsKey(memberId)) {
            System.out.println("Member ID not found. Please try again.");
            return;
        }
        System.out.println("Enter book ID: ");
        String bookId = sc.nextLine();
        // Check if book exists
        if (!books.containsKey(bookId)) {
            System.out.println("Book ID not found. Please try again.");
            return;
        }
        // Update the book's borrowed count and record the return transaction       
        Book book = books.get(bookId);
        if (book.getBook_borrowed() <= 0) {
                System.out.println("Error: No copies of this book are currently borrowed.");
                return;
        }
        book.setBook_borrowed(book.getBook_borrowed() - 1);
        // Find the corresponding borrow record and update the return date
        for (Borrow borrow : borrowingRecords) {
            if (borrow.getMember_id().equals(memberId) && borrow.getBook_id().equals(bookId) && borrow.getReturn_date() == null) {
                borrow.setReturn_date(java.time.LocalDate.now().toString());
                break;
            }
        }
        
        System.out.println("Book returned successfully.");
    }  

    //View borrowing records
    public void viewBorrowingRecords() {
        // Code to view borrowing records
        if (borrowingRecords.isEmpty()) {
            System.out.println("No borrowing records found.");
        } else {
            System.out.println("Borrowing Records:");
            for (Borrow borrow : borrowingRecords) {
                System.out.println("Borrow ID: " + borrow.getBorrow_id() + ", Member ID: " + borrow.getMember_id() + ", Book ID: " + borrow.getBook_id() + ", Borrow Date: " + borrow.getBorrow_date() + ", Return Date: " + borrow.getReturn_date());
            }
        }
    }


    //Check valid
    public boolean isValidInt(String str, int min, int max) {
        try {
            int num = Integer.parseInt(str);
            return num >= min && num <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

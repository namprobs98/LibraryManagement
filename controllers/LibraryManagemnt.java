package Java.LibraryManagement.controllers;

import java.util.Scanner;

import Java.LibraryManagement.models.Book;
import Java.LibraryManagement.models.Borrow;
import Java.LibraryManagement.models.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LibraryManagemnt {
    Scanner sc = new Scanner(System.in);
    Map<String, Book> books = new HashMap<>();
    Map<String, Member> members = new HashMap<>();
     List<Borrow> borrowingRecords = new ArrayList<>(); 

    public void manageLibrary() {
        System.out.println();
        System.out.println("Library Management System");
        System.out.println("___________________________________________________");
        System.out.println("1. Manage Book");
        System.out.println("2. Manage Member");
        System.out.println("3. Manage Borrowing");
        System.out.println("4. Exit");

        //Thêm mẫu dữ liệu
        initSampleBooks();
        initSampleMembers();
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
                    // Code to manage books
                    BookManagement bookManagement = new BookManagement(books);
                    bookManagement.manageBooks();
                    break;
                case 2:
                    // Code to manage members
                    MemberManagement memberManagement = new MemberManagement(members);
                    memberManagement.manageMembers();
                    break;
                case 3:
                    // Code to manage borrowing
                    BorrowingManagement borrowingManagement = new BorrowingManagement(books, members, borrowingRecords);
                    borrowingManagement.manageBorrowing();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
            }
        }
    }

    public boolean isValidInt(String str, int min, int max) {
        try {
            int num = Integer.parseInt(str);
            return num >= min && num <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    
    // Thêm 10 sách mẫu vào map books
    private void initSampleBooks() {
        books.put("B001", new Book("B001", "Java Basics", "John Doe", "Programming", 5));
        books.put("B002", new Book("B002", "Python 101", "Jane Smith", "Programming", 7));
        books.put("B003", new Book("B003", "C++ Primer", "Alex Brown", "Programming", 4));
        books.put("B004", new Book("B004", "Data Structures", "Emily White", "Computer Science", 6));
        books.put("B005", new Book("B005", "Algorithms", "David Green", "Computer Science", 3));
        books.put("B006", new Book("B006", "Database Systems", "Chris Black", "IT", 8));
        books.put("B007", new Book("B007", "Operating Systems", "Anna Blue", "IT", 2));
        books.put("B008", new Book("B008", "Networking", "Tom Red", "IT", 5));
        books.put("B009", new Book("B009", "Web Development", "Sara Yellow", "Programming", 9));
        books.put("B010", new Book("B010", "Machine Learning", "Mike Purple", "AI", 4));
    }

    // Thêm 10 thành viên mẫu vào map members
    private void initSampleMembers() {
        members.put("M001", new Member("M001", "Alice Johnson", "alice@example.com", "1234567890"));
        members.put("M002", new Member("M002", "Bob Smith", "bob@example.com", "0987654321"));
        members.put("M003", new Member("M003", "Charlie Brown", "charlie@example.com", "1122334455"));
        members.put("M004", new Member("M004", "Diana Prince", "diana@example.com", "5544332211"));
        members.put("M005", new Member("M005", "Eve Wilson", "eve@example.com", "6677889900"));
        members.put("M006", new Member("M006", "Frank Miller", "frank@example.com", "0099887766"));
        members.put("M007", new Member("M007", "Grace Lee", "grace@example.com", "1122334455"));
        members.put("M008", new Member("M008", "Henry Davis", "henry@example.com", "5544332211"));
        members.put("M009", new Member("M009", "Ivy Chen", "ivy@example.com", "6677889900"));
        members.put("M010", new Member("M01<PASSWORD>", "Jack Taylor", "jack@example.com", "0<PASSWORD>"));
    }
}
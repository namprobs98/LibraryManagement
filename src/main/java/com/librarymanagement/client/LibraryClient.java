package com.librarymanagement.client;

import com.librarymanagement.controller.BookController;
import com.librarymanagement.controller.BorrowController;
import com.librarymanagement.controller.MemberController;
import com.librarymanagement.controller.StorageController;
import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.BorrowRecord;
import com.librarymanagement.entity.Member;
import com.librarymanagement.service.BootstrapService;
import com.librarymanagement.service.StorageMode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LibraryClient implements CommandLineRunner {
    private final BookController bookController;
    private final MemberController memberController;
    private final BorrowController borrowController;
    private final StorageController storageController;
    private final BootstrapService bootstrapService;

    public LibraryClient(
            BookController bookController,
            MemberController memberController,
            BorrowController borrowController,
            StorageController storageController,
            BootstrapService bootstrapService
    ) {
        this.bookController = bookController;
        this.memberController = memberController;
        this.borrowController = borrowController;
        this.storageController = storageController;
        this.bootstrapService = bootstrapService;
    }

    @Override
    public void run(String... args) {
        bootstrapService.seedIfEmpty();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n=== Library Management (" + storageController.getMode() + ") ===");
            System.out.println("1. Manage books");
            System.out.println("2. Manage members");
            System.out.println("3. Manage borrowing");
            System.out.println("4. Change storage format (MEMORY/TXT/EXCEL)");
            System.out.println("5. Exit");
            choice = readInt(sc, 1, 5);
            switch (choice) {
                case 1 -> manageBooks(sc);
                case 2 -> manageMembers(sc);
                case 3 -> manageBorrowing(sc);
                case 4 -> chooseStorage(sc);
                case 5 -> System.out.println("Goodbye!");
                default -> { }
            }
        }
    }

    private void manageBooks(Scanner sc) {
        int choice = 0;
        while (choice != 7) {
            System.out.println("\n--- Book Menu ---");
            System.out.println("1. Add");
            System.out.println("2. View by id");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Search by title");
            System.out.println("6. List all");
            System.out.println("7. Back");
            choice = readInt(sc, 1, 7);
            switch (choice) {
                case 1 -> {
                    System.out.print("Book id: ");
                    String id = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Copies: ");
                    int copies = readInt(sc, 1, Integer.MAX_VALUE);
                    System.out.println(bookController.addBook(id, title, author, genre, copies) ? "Added." : "Book id already exists.");
                }
                case 2 -> {
                    System.out.print("Book id: ");
                    String id = sc.nextLine();
                    System.out.println(bookController.getBookById(id).map(this::bookToText).orElse("Not found."));
                }
                case 3 -> {
                    System.out.print("Book id: ");
                    String id = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Copies: ");
                    int copies = readInt(sc, 1, Integer.MAX_VALUE);
                    System.out.println(bookController.updateBook(id, title, author, genre, copies) ? "Updated." : "Book not found.");
                }
                case 4 -> {
                    System.out.print("Book id: ");
                    String id = sc.nextLine();
                    System.out.println(bookController.deleteBook(id) ? "Deleted." : "Book not found.");
                }
                case 5 -> {
                    System.out.print("Keyword: ");
                    String keyword = sc.nextLine().toLowerCase();
                    List<Book> books = bookController.getAllBooks().stream()
                            .filter(b -> b.getTitle().toLowerCase().contains(keyword))
                            .toList();
                    if (books.isEmpty()) {
                        System.out.println("No match.");
                    } else {
                        books.forEach(b -> System.out.println(bookToText(b)));
                    }
                }
                case 6 -> {
                    List<Book> books = bookController.getAllBooks();
                    if (books.isEmpty()) System.out.println("No books.");
                    books.forEach(b -> System.out.println(bookToText(b)));
                }
                case 7 -> { }
                default -> { }
            }
        }
    }

    private void manageMembers(Scanner sc) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. Add");
            System.out.println("2. List all");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back");
            choice = readInt(sc, 1, 5);
            switch (choice) {
                case 1 -> {
                    System.out.print("Member id: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.println(memberController.addMember(id, name, email, phone) ? "Added." : "Member id already exists.");
                }
                case 2 -> {
                    List<Member> members = memberController.getAllMembers();
                    if (members.isEmpty()) System.out.println("No members.");
                    members.forEach(m -> System.out.println(memberToText(m)));
                }
                case 3 -> {
                    System.out.print("Member id: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.println(memberController.updateMember(id, name, email, phone) ? "Updated." : "Member not found.");
                }
                case 4 -> {
                    System.out.print("Member id: ");
                    String id = sc.nextLine();
                    System.out.println(memberController.deleteMember(id) ? "Deleted." : "Member not found.");
                }
                case 5 -> { }
                default -> { }
            }
        }
    }

    private void manageBorrowing(Scanner sc) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n--- Borrow Menu ---");
            System.out.println("1. Borrow book");
            System.out.println("2. Return book");
            System.out.println("3. View records");
            System.out.println("4. Back");
            choice = readInt(sc, 1, 4);
            switch (choice) {
                case 1 -> {
                    System.out.print("Member id: ");
                    String memberId = sc.nextLine();
                    System.out.print("Book id: ");
                    String bookId = sc.nextLine();
                    System.out.println(borrowController.borrowBook(memberId, bookId));
                }
                case 2 -> {
                    System.out.print("Member id: ");
                    String memberId = sc.nextLine();
                    System.out.print("Book id: ");
                    String bookId = sc.nextLine();
                    System.out.println(borrowController.returnBook(memberId, bookId));
                }
                case 3 -> {
                    List<BorrowRecord> records = borrowController.getRecords();
                    if (records.isEmpty()) System.out.println("No records.");
                    records.forEach(r -> System.out.println(recordToText(r)));
                }
                case 4 -> { }
                default -> { }
            }
        }
    }

    private void chooseStorage(Scanner sc) {
        System.out.println("\nChoose save format:");
        System.out.println("1. MEMORY (arraylist/map)");
        System.out.println("2. TXT");
        System.out.println("3. EXCEL");
        int c = readInt(sc, 1, 3);
        StorageMode mode = c == 1 ? StorageMode.MEMORY : c == 2 ? StorageMode.TXT : StorageMode.EXCEL;
        storageController.switchMode(mode);
        System.out.println("Switched to " + mode + ". Data changes will be synced there.");
    }

    private static int readInt(Scanner sc, int min, int max) {
        while (true) {
            System.out.print("Choice: ");
            String raw = sc.nextLine();
            try {
                int value = Integer.parseInt(raw);
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid number, expected " + min + " - " + max + ".");
        }
    }

    private String bookToText(Book b) {
        return "Book{id='%s', title='%s', author='%s', genre='%s', copies=%d, borrowed=%d}"
                .formatted(b.getId(), b.getTitle(), b.getAuthor(), b.getGenre(), b.getCopies(), b.getBorrowed());
    }

    private String memberToText(Member m) {
        return "Member{id='%s', name='%s', email='%s', phone='%s'}"
                .formatted(m.getId(), m.getName(), m.getEmail(), m.getPhone());
    }

    private String recordToText(BorrowRecord r) {
        return "Borrow{id='%s', member='%s', book='%s', borrowDate='%s', returnDate='%s'}"
                .formatted(r.getId(), r.getMemberId(), r.getBookId(), r.getBorrowDate(), r.getReturnDate());
    }
}

package com.librarymanagement.controller;

import com.librarymanagement.entity.BorrowRecord;
import com.librarymanagement.service.BorrowService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    public String borrowBook(String memberId, String bookId) {
        return borrowService.borrowBook(memberId, bookId);
    }

    public String returnBook(String memberId, String bookId) {
        return borrowService.returnBook(memberId, bookId);
    }

    public List<BorrowRecord> getRecords() {
        return borrowService.getRecords();
    }
}

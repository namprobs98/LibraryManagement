package com.librarymanagement.repository;

import com.librarymanagement.entity.BorrowRecord;
import java.util.List;

public interface BorrowRecordRepository {
    void save(BorrowRecord record);
    List<BorrowRecord> findAll();
    void replaceAll(List<BorrowRecord> records);
}

package com.librarymanagement.repository;

import com.librarymanagement.entity.BorrowRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBorrowRecordRepository implements BorrowRecordRepository {
    private final List<BorrowRecord> data = new ArrayList<>();

    @Override
    public void save(BorrowRecord record) {
        data.add(record);
    }

    @Override
    public List<BorrowRecord> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public void replaceAll(List<BorrowRecord> records) {
        data.clear();
        data.addAll(records);
    }
}

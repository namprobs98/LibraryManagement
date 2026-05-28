package com.librarymanagement.repository;

import com.librarymanagement.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> data = new LinkedHashMap<>();

    @Override
    public void save(Book book) {
        data.put(book.getId(), book);
    }

    @Override
    public Optional<Book> findById(String id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteById(String id) {
        data.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return data.containsKey(id);
    }

    @Override
    public void replaceAll(List<Book> books) {
        data.clear();
        for (Book book : books) {
            data.put(book.getId(), book);
        }
    }
}

package com.example.apitest.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public void save(Book book) {
        bookRepository.save(book);
    }
}

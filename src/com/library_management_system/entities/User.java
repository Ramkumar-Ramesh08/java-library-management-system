package com.library_management_system.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private String id;
    private String name;
    private List<Book> borrowedBooks;


    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;

    }

    public void setBorrowedBooks(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }


    public List<Book> listAllBooks() {
        return books;
    }
}



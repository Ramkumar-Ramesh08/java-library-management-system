package com.library_management_system.service;

import com.library_management_system.entities.Book;
import com.library_management_system.entities.User;
import com.library_management_system.exception.BookNotFoundException;
import com.library_management_system.exception.UserNotFoundException;

import java.util.*;

public class LibraryManagementImpl implements LibraryManagement{
     private Map<String, Book> bookMap = new HashMap<>();
     private Map<String, User> userMap = new HashMap<>();

    public String addNewBook(String title, String author) {
        if (title == null || author == null || title.isEmpty() || author.isEmpty()) {
            return "title and author should not be null or empty";
        }
        Book book = new Book(title, author);
        bookMap.put(book.getId(), book);
        return book.getId();

    }

    public String registerUser(String name) {
        if (name == null || name.isEmpty()) {
            return "user name must not be null or empty";
        }
        User user = new User(name);
        userMap.put(user.getId(), user);
        return user.getId();
    }

    public String borrowBook(String userId, String bookId) {
        if (!bookMap.containsKey(bookId)) {
            throw new BookNotFoundException("Book doesn't exit ! Please send the valid bookId");
        }
        if (!userMap.containsKey(userId)) {
            throw new UserNotFoundException("User doesn't exit! please send the valid userId");

         }
        User user = userMap.get(userId);
        Book book = bookMap.get(bookId);

        if (!book.isAvailable()) {
            return "book is not available";
        }
        if (user.getBorrowedBooks().size() >= 3) {
            return "user already borrowed 3 books";
        }
        book.setAvailable(false);
        user.setBorrowedBooks(book);
        return "book borrowed successfully";
    }

    public String returnBook(String userId, String bookId) {

        if (bookMap.containsKey(bookId)) {
            return "book not found";
        }
        if (userMap.containsKey(userId)) {
            return "user not found ";
        }

        User user = userMap.get(userId);
        Book book = bookMap.get(bookId);


        if (!user.getBorrowedBooks().contains(book)) {
            return "book not borrowed by this user";
        }
        user.returnBook(book);
        book.setAvailable(true);
        return "book returned successfully";

    }

    public List<Book> listAllBooks() {
        if (bookMap.isEmpty()) {
            System.out.println("no books in the library");
            return new ArrayList<>();
        }
        List<Book> books = new ArrayList<>(bookMap.values());
        for (Book book : books) {
            System.out.println(book);
        }
        return books;
    }

    public List <Book> listBorrowedBooks(String userId) {
        if (userMap.containsKey(userId)) {
            System.out.println("user not found");
            return;
        }
        User user = userMap.get(userId);
        List<Book> borrowed = user.getBorrowedBooks();
        if (borrowed.isEmpty())
            System.out.println("no books borrowed");
        for (Book book : borrowed)
            System.out.println(book);


    }

    public Book getBookById(String bookId){
        return bookMap.get(bookId);




    }
}


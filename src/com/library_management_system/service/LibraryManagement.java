package com.library_management_system.service;
import com.library_management_system.Driver;
import com.library_management_system.entities.Book;
import com.library_management_system.exception.BookNotFoundException;

import java.util.List;


public interface LibraryManagement {
    String addNewBook(String title, String author);
    String registerUser(String name) ;
    String borrowBook(String userId, String bookId) throws BookNotFoundException;
    String returnBook(String userId, String bookId);
    List<Book> listAllBooks();
    List <Book>listBorrowedBooks(String userId);


}

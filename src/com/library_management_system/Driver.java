package com.library_management_system;

import com.library_management_system.service.LibraryManagement;
import com.library_management_system.service.LibraryManagementImpl;

public class Driver {

    public static void main(String[] args) {
        LibraryManagement libraryManagement= new LibraryManagementImpl();
        System.out.println(libraryManagement.registerUser(null));
//        libraryManagement.addNewBook("think and grow rich","napoleon hill");
//        libraryManagement.listAllBooks();
//        libraryManagement.returnBook("101","100");
//        libraryManagement.listBorrowedBooks("103");
//        libraryManagement.borrowBook("192","150");
    }
}

package test.com.libary_mangaement_system;

import com.library_management_system.entities.Book;
import com.library_management_system.exception.BookNotFoundException;
import com.library_management_system.exception.UserNotFoundException;
import com.library_management_system.service.LibraryManagementImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryManagementImplTest {

    private LibraryManagementImpl libraryManagementImpl = new LibraryManagementImpl();

    /*
    Add new Book

    1. Book Title or Author shouldn't be null or empty

    2. If title and author is not empty, it return success response

    */

    @Test
    void shouldReturnErrorMessage_WhenAuthorIsNull() {
        assertEquals("title and author should not be null or empty", libraryManagementImpl.addNewBook("Test title", null));
    }

    @Test
    void shouldReturnErrorMessage_WhenTitleIsEmpty() {
        assertEquals("title and author should not be null or empty",
                libraryManagementImpl.addNewBook("", "Test"));
    }

    @Test
    void shouldReturnSuccessMessage_WhenAuthorAndTitleIsNotEmpty() {
        assertNotNull(libraryManagementImpl.addNewBook("Test Title", "Test Author"));
    }

    @Test
    void shouldReturnErrorMessage_whenNameIsNull() {
        assertEquals("user name must not be null or empty", libraryManagementImpl.registerUser(""));
    }

    @Test
    void shouldReturnErrorMessage_whenNameIsEmpty() {
        assertEquals("user name must not be null or empty", libraryManagementImpl.registerUser(""));
    }

    @Test
    void ShouldReturnSuccessMessage_WhenNameIsNotEmpty() {
        assertNotNull(libraryManagementImpl.registerUser("ram"));
    }

    @Test
    void ShouldReturnErrorMessage_WhenBookIsFound() {
        assertThrows(BookNotFoundException.class, () -> libraryManagementImpl.borrowBook("101", ""));
    }

    @Test
    void ShouldReturnErrorMessage_whenUserIsFound() {
        String bookId = libraryManagementImpl.addNewBook("test title", "test author");
        assertThrows(UserNotFoundException.class, () -> libraryManagementImpl.borrowBook("", "102"));

    }

    @Test
    void ShouldReturnErrorMessage_WhenBookIsNotAvailable() {
        String bookId = libraryManagementImpl.addNewBook("Test title", "Test author");
        String userId = libraryManagementImpl.registerUser("ram");
        Book book = libraryManagementImpl.getBookById(bookId);
        book.setAvailable(false);
        assertEquals("book is not available", libraryManagementImpl.borrowBook(userId, bookId));
    }

    @Test
    void ShouldReturnSuccessMessage_WhenReturningBorrowedBook() {
        String userId = libraryManagementImpl.registerUser("ram");
        String bookId = libraryManagementImpl.addNewBook("test title", "test author");
        libraryManagementImpl.borrowBook(userId, bookId);
        String result = libraryManagementImpl.returnBook("101", "102");
        assertEquals("book returned successfully", result);

    }

    @Test
    void ShouldReturnErrorMessage_WhenReturnBook() {
        assertEquals("book not found", libraryManagementImpl.returnBook("101", ""));

    }

    @Test
    void ShouldErrorReturnMessage_WhenUserIsFound() {
        String bookId = libraryManagementImpl.addNewBook("test title", "test author");
        assertEquals("user not found", libraryManagementImpl.returnBook("", bookId));
    }

    @Test
    void ShouldReturnError_whenReturningBookNotBorrowedByUser() {
        String result = libraryManagementImpl.returnBook("101", "102");
        assertEquals("book not borrowed by the user", result);
    }

    @Test
    void ShouldSuccessMessage_WhenReturningBook() {
        String result = libraryManagementImpl.returnBook("userId", "bookId");
        assertEquals("book returned successfully", result);
    }

    @Test
    void ShouldReturnMessageListAllBooksSuccessfully() {
        libraryManagementImpl.addNewBook("test title", "test author");
        libraryManagementImpl.addNewBook("test title", "test author");
        List<Book> books = libraryManagementImpl.listAllBooks();
        assertEquals(3, books.size());
    }

    @Test
    void ShouldErrorBorrowBookMessage_WhenUserIsFound() {
        String bookId = libraryManagementImpl.addNewBook("test title", "test author");
        assertEquals("user not found", libraryManagementImpl.borrowBook("", bookId));
    }

    @Test
    void ShouldListBorrowedBooksForUser() {
        String userId = libraryManagementImpl.registerUser("ram");
        String bookId = libraryManagementImpl.addNewBook("test title", "test author");
        libraryManagementImpl.borrowBook(userId, bookId);
        List<Book> borrowed = libraryManagementImpl.listBorrowedBooks(userId);
        assertEquals(2, borrowed.size());
        assertEquals("test title", borrowed.get(0).getTitle());
    }
}






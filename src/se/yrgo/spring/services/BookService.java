package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

public interface BookService {
    List<Book> getAllBooksByAuthor(String author);

    List<Book> getAllRecommendedBooks(String userId);

    Book getBookByIsbn(String isbn) throws BookNotFoundException;

    List<Book> getEntireCatalogue();

    void registerNewBook(Book newBook);

    void deleteBookFromStock(Book bookToRemove) throws BookNotFoundException;
}

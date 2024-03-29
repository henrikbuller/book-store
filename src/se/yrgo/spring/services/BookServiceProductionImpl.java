package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.data.BookDao;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

@Transactional
@Service
public class BookServiceProductionImpl implements BookService {

    @Autowired
    private BookDao dao;

    public BookServiceProductionImpl(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return dao.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllRecommendedBooks(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        return dao.findByIsbn(isbn);
    }

    @Override
    public List<Book> getEntireCatalogue() {
        return dao.allBooks();
    }

    @Override
    public void registerNewBook(Book newBook) {
        dao.create(newBook);

    }

    @Override
    public void deleteBookFromStock(Book bookToRemove) throws BookNotFoundException {
        dao.delete(bookToRemove);
    }


}
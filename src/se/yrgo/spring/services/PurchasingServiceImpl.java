package se.yrgo.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

@Transactional
@Component
public class PurchasingServiceImpl implements PurchasingService {
    @Autowired
    private AccountsService accounts;
    @Qualifier("bookService")
    @Autowired
    private BookService books;

    @Override
    public void buyBook(String isbn) throws BookNotFoundException {
        //To find the book
        Book book = books.getBookByIsbn(isbn);

        //Delete from stock
        books.deleteBookFromStock(books.getBookByIsbn(isbn));

        //Raise invoice
        accounts.raiseInvoice(book);

    }
    public PurchasingServiceImpl(AccountsService accounts, BookService books) {
        this.accounts = accounts;
        this.books = books;
    }
}

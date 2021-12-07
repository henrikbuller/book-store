package se.yrgo.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

@Transactional
@Service
public class PurchasingServiceImpl implements PurchasingService {
    @Autowired
    private AccountsService accounts;

    @Autowired
    private BookService books;

    @Override
    @Transactional(rollbackFor = {CustomerCreditExceededException.class, BookNotFoundException.class})
    public void buyBook(String isbn) throws CustomerCreditExceededException, BookNotFoundException {
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

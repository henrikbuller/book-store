package se.yrgo.spring.services;

import org.springframework.stereotype.Service;
import se.yrgo.spring.domain.Book;

@Service
public class AccountsServiceMockImpl implements AccountsService
{
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException {
        // a very basic implementation for testing
        System.out.println("Raised the invoice for " + requiredBook);
        throw new CustomerCreditExceededException();
    }
}
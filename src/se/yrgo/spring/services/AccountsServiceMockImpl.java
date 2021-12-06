package se.yrgo.spring.services;

import org.springframework.stereotype.Component;
import se.yrgo.spring.domain.Book;

@Component
public class AccountsServiceMockImpl implements AccountsService
{
    public void raiseInvoice(Book requiredBook)
    {
        // a very basic implementation for testing
        System.out.println("Raised the invoice for " + requiredBook);
    }
}
package se.yrgo.spring.services;

import se.yrgo.spring.domain.Book;

public interface AccountsService
{
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException;
}
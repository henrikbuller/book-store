package se.yrgo.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.CustomerCreditExceededException;
import se.yrgo.spring.services.PurchasingService;

public class Client {
	public static void main(String[] args) throws BookNotFoundException {
		ClassPathXmlApplicationContext container = new
				ClassPathXmlApplicationContext("application.xml");

		try {
			PurchasingService purchasing = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean(BookService.class);

			//begin
			bookService.registerNewBook(new Book("0123456789", "Spring" , "Author", 20.00) );
			//commit

			//begin
			try {
				purchasing.buyBook("0123456789");
			} catch (CustomerCreditExceededException e) {
				System.err.println("Sorry, you do not have enough money.");
			}
			//commit
		} finally {
			container.close();
		}

		container.close();
	}
}
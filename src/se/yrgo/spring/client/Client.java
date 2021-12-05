package se.yrgo.spring.client;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.PurchasingService;

public class Client {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext container = new
				ClassPathXmlApplicationContext("application.xml");

		try {
			PurchasingService purchasing = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean(BookService.class);
			bookService.registerNewBook(new Book("0123456789", "Spring" , "Author", 20.00) );
			try {
				purchasing.buyBook("0123456789");
			} catch (Exception e) {
				System.err.println("Sorry, this book doesn't exist.");
			}
		} finally {
			container.close();

		}

		container.close();
	}
}
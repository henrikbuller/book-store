package se.yrgo.spring.client;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;

public class Client {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext container = new
				ClassPathXmlApplicationContext("application.xml");

		try {
			BookService bookService = container.getBean("bookService" , BookService.class);

			bookService.registerNewBook(new Book("1234596896812", "Birds",
					"Bird Lover", 100.00));
			List<Book> allBooks = bookService.getEntireCatalogue();
			for(Book book:allBooks) {
				System.out.println(book);
			}
		} finally {
			container.close();
		}

		container.close();
	}
}
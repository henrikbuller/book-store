package se.yrgo.spring.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import se.yrgo.spring.domain.Book;
import org.hibernate.query.Query;


@Repository
public class BookDaoHibernate implements BookDao {

    @Autowired
    private HibernateTemplate template;

    @Override
    public List<Book> allBooks() {
        return template.execute(session -> session.createQuery("from Book").list());

    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        List<Book> results = template.execute(session -> {
            Query<Book> q = session.createQuery("from Book where isbn=?1");
            q.setParameter(1, isbn);
            return q.list();
        });
        if (results.isEmpty()) {
            throw new BookNotFoundException();
        }
        return results.get(0);
    }

    @Override
    public void create(Book newBook) {
        template.save(newBook);

    }

    @Override
    public void delete(Book redundantBook) {
        Book foundBook = template.get(Book.class, redundantBook.getId());
        template.delete(foundBook);

    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return template.execute(session -> {
            Query<Book> q = session.createQuery("from Book where author=:author");
            q.setParameter("author", author);
            return q.list();
        });
    }

}
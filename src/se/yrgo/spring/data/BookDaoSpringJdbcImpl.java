package se.yrgo.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import se.yrgo.spring.domain.Book;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("bookService")
public class BookDaoSpringJdbcImpl implements BookDao {
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
    private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
    private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";

    @Autowired
    public BookDaoSpringJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @PostConstruct
    public void createTables() {
        try {
        jdbcTemplate.update(CREATE_TABLE_SQL);
        } catch(BadSqlGrammarException e) {
            System.err.println("Table already exists");
        }
    }
    @Override
    public List<Book> allBooks() {
        return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        try {
            return jdbcTemplate.queryForObject("select * from Book where ISBN=?",
                    new BookMapper(), isbn);
        } catch(EmptyResultDataAccessException e) {
            throw new BookNotFoundException();
        }
    }

    @Override
    public void create(Book newBook) {
        jdbcTemplate.update(INSERT_BOOK_SQL, newBook.getIsbn(),
                newBook.getTitle(), newBook.getAuthor(), newBook.getPrice());
    }

    @Override
    public void delete(Book redundantBook) {
        jdbcTemplate.update("Delete from Book where ISBN=?",
                redundantBook.getIsbn());
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return jdbcTemplate.query("select * from Book where author=?", new
                BookMapper(), author);
    }

    class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {
            String isbn = rs.getString("ISBN");
            String title = rs.getString("title");
            String author = rs.getString("author");
            double price = rs.getDouble("price");
            Book book = new Book(isbn, title, author, price);
            return book;
        }
    }
}

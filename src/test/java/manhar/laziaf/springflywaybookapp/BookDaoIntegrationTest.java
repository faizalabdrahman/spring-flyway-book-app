package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.dao.BookDao;
import manhar.laziaf.springflywaybookapp.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"manhar.laziaf.springflywaybookapp.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest
{
    @Autowired
    BookDao bookDao;

    @Test
    public void testGetById()
    {
        Book fetchedBook = bookDao.getById(1L);

        assertNotNull(fetchedBook);

    }

    @Test
    public void testFindBookByTitle()
    {
        Book fetchedBook = bookDao.findBookByTitle("Data Driven Design");

        assertNotNull(fetchedBook);
    }

    @Test
    public void testSaveNewBook()
    {
        Book book = new Book("Introduction to Algorithms", "321", "Random House", 3L);
        Book savedBook = bookDao.saveNewBook(book);

        assertNotNull(savedBook);
    }

    @Test
    public void testUpdateBook()
    {
        Book book = new Book("Dirty Code", "876", "Random House", 3L);
        Book savedBook = bookDao.saveNewBook(book);

        savedBook.setTitle("Clean Code");
        Book updatedBook = bookDao.updateBook(savedBook);

        assertEquals("Clean Code", updatedBook.getTitle());
    }

    @Test
    public void testDeleteBookById()
    {
        Book book = new Book("Effective Java", "543", "Random House", 5L);
        Book savedBook = bookDao.saveNewBook(book);

        bookDao.deleteBookById(savedBook.getId());

        Book fetchedBook = bookDao.getById(savedBook.getId());

        assertNull(fetchedBook);
    }
}

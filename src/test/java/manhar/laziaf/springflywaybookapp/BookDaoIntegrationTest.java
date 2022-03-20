package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.dao.BookDao;
import manhar.laziaf.springflywaybookapp.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}

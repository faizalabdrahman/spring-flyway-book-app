package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.dao.AuthorDao;
import manhar.laziaf.springflywaybookapp.domain.Author;
import manhar.laziaf.springflywaybookapp.repositories.AuthorRepository;
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
public class AuthorDaoIntegrationTest
{
    @Autowired
    AuthorDao authorDao;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void testGetAuthor()
    {
        Author fetchAuthor = authorDao.getById(1L);

        assertNotNull(fetchAuthor);
    }

    @Test
    public void testFindAuthorByName()
    {
        Author author = authorDao.findAuthorByName("Mohamed", "Salah");

        assertNotNull(author);
    }

    @Test
    public void testSaveAuthor()
    {
        Author author = new Author("Muhammad", "Faizal");
        Author savedAuthor = authorDao.saveNewAuthor(author);

        assertNotNull(savedAuthor);
    }

    @Test
    public void testUpdateAuthor()
    {
        Author author = new Author("Muhammad", "F");
        Author savedAuthor = authorDao.saveNewAuthor(author);

        savedAuthor.setLastName("Faizal");
        Author updatedAuthor = authorDao.updateAuthor(savedAuthor);

        assertEquals("Faizal", updatedAuthor.getLastName());
    }

    @Test
    public void testDeleteAuthor()
    {
        Author author = new Author("Bret", "Hart");
        Author savedAuthor = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(savedAuthor.getId());

        Author fetchedAuthor = authorDao.getById(savedAuthor.getId());

        assertNull(fetchedAuthor);
    }
}

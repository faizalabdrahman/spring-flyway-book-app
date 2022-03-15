package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.dao.AuthorDao;
import manhar.laziaf.springflywaybookapp.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles({"local"})
@DataJpaTest
@ComponentScan(basePackages = {"manhar.laziaf.springflywaybookapp.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest
{
    @Autowired
    AuthorDao authorDao;

    @Test
    public void testGetAuthor()
    {
        Author author = authorDao.getById(1L);

        assertNotNull(author);
    }
}

package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.domain.AuthorUuid;
import manhar.laziaf.springflywaybookapp.domain.BookUuid;
import manhar.laziaf.springflywaybookapp.repositories.AuthorUuidRepository;
import manhar.laziaf.springflywaybookapp.repositories.BookRepository;
import manhar.laziaf.springflywaybookapp.repositories.BookUuidRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"manhar.laziaf.springflywaybookapp.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest
{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Test
    public void testMySql()
    {
        long countBefore = bookRepository.count();

        assertThat(countBefore).isEqualTo(2);

    }

    @Test
    public void testBookUuid()
    {
        BookUuid bookUuid = new BookUuid("Clean Code", "123", "Random House");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);

        BookUuid fetchedBookUuid = bookUuidRepository.getById(savedBookUuid.getId());

        assertNotNull(savedBookUuid.getId());
        assertNotNull(fetchedBookUuid.getId());
    }

    @Test
    public void testAuthorUuid()
    {
        AuthorUuid authorUuid = new AuthorUuid("Robert", "Cecil");
        AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);

        AuthorUuid fetchedAuthorUuid = authorUuidRepository.getById(savedAuthorUuid.getId());

        assertNotNull(savedAuthorUuid.getId());
        assertNotNull(fetchedAuthorUuid.getId());
    }
}

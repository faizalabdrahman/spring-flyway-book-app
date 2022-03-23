package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.domain.AuthorUuid;
import manhar.laziaf.springflywaybookapp.domain.BookNatural;
import manhar.laziaf.springflywaybookapp.domain.BookUuid;
import manhar.laziaf.springflywaybookapp.domain.composite.AuthorComposite;
import manhar.laziaf.springflywaybookapp.domain.composite.AuthorEmbedded;
import manhar.laziaf.springflywaybookapp.domain.composite.NameId;
import manhar.laziaf.springflywaybookapp.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

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

    @Test
    public void testBookNatural()
    {
        BookNatural bookNatural = new BookNatural("Design Pattern", "987", "Random House");
        BookNatural savedBookNatural = bookNaturalRepository.save(bookNatural);

        BookNatural fetchBookNatural = bookNaturalRepository.getById(savedBookNatural.getTitle());

        assertEquals(savedBookNatural.getTitle(), fetchBookNatural.getTitle());
    }

    @Test
    public void testAuthorComposite()
    {
        NameId nameId = new NameId("Jon", "Stokes");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("Singapore");

        AuthorComposite savedAuthorComposite = authorCompositeRepository.save(authorComposite);

        AuthorComposite fetchAuthorComposite = authorCompositeRepository.getById(nameId);

        assertNotNull(savedAuthorComposite);
        assertNotNull(fetchAuthorComposite);
    }

    @Test
    public void testAuthorEmbeddedComposite()
    {
        NameId nameId = new NameId("Jon", "Stokes");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded();
        authorEmbedded.setNameId(nameId);
        authorEmbedded.setCountry("Singapore");

        AuthorEmbedded savedAuthorEmbedded = authorEmbeddedRepository.save(authorEmbedded);

        AuthorEmbedded fetchAuthorEmbedded = authorEmbeddedRepository.getById(savedAuthorEmbedded.getNameId());

        assertNotNull(savedAuthorEmbedded);
        assertNotNull(fetchAuthorEmbedded);
    }
}

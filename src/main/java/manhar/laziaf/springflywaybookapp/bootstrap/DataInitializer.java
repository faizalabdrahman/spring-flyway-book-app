package manhar.laziaf.springflywaybookapp.bootstrap;

import manhar.laziaf.springflywaybookapp.domain.Author;
import manhar.laziaf.springflywaybookapp.domain.AuthorUuid;
import manhar.laziaf.springflywaybookapp.domain.Book;
import manhar.laziaf.springflywaybookapp.domain.BookUuid;
import manhar.laziaf.springflywaybookapp.repositories.AuthorRepository;
import manhar.laziaf.springflywaybookapp.repositories.AuthorUuidRepository;
import manhar.laziaf.springflywaybookapp.repositories.BookRepository;
import manhar.laziaf.springflywaybookapp.repositories.BookUuidRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner
{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository)
    {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        bookRepository.deleteAll();

        Book bookDDD = new Book("Data Driven Design", "123", "Random House", null);
        bookRepository.save(bookDDD);

        Book bookSIA = new Book("Spring In Action", "456", "Random House", null);
        bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });

        Author author = new Author("Mohamed", "Salah");
        Author savedAuthor = authorRepository.save(author);
        System.out.println("Saved Author Id: " + savedAuthor.getId());

        AuthorUuid authorUuid = new AuthorUuid("Joe", "Buck");
        AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + savedAuthorUuid.getId());

        BookUuid bookUuid = new BookUuid("Java Performance", "789", "Random House");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " +savedBookUuid.getId());
    }
}

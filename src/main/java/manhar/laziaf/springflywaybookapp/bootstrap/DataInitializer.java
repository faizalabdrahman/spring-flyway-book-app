package manhar.laziaf.springflywaybookapp.bootstrap;

import manhar.laziaf.springflywaybookapp.domain.AuthorUuid;
import manhar.laziaf.springflywaybookapp.domain.Book;
import manhar.laziaf.springflywaybookapp.repositories.AuthorUuidRepository;
import manhar.laziaf.springflywaybookapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner
{
    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository)
    {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
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

        AuthorUuid authorUuid = new AuthorUuid("Joe", "Buck");
        AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);

        System.out.println("Saved Author UUID: " + savedAuthor.getId());
    }
}

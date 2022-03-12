package manhar.laziaf.springflywaybookapp.bootstrap;

import manhar.laziaf.springflywaybookapp.domain.Book;
import manhar.laziaf.springflywaybookapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner
{
    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        bookRepository.deleteAll();

        Book bookDDD = new Book("Data Driven Design", "123", "Random House");
        bookRepository.save(bookDDD);

        Book bookSIA = new Book("Spring In Action", "456", "Random House");
        bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });


    }
}
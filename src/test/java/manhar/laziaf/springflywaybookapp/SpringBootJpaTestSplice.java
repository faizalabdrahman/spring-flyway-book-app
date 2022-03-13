package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.domain.Book;
import manhar.laziaf.springflywaybookapp.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = {"manhar.laziaf.springflywaybookapp.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSplice
{
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testJpaTestSplice()
    {
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("My Book", "789", "Random House", null));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }
}

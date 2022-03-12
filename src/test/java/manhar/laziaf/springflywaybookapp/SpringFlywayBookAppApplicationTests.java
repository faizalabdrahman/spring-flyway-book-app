package manhar.laziaf.springflywaybookapp;

import manhar.laziaf.springflywaybookapp.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SpringFlywayBookAppApplicationTests
{
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBookRepository()
    {
        long count = bookRepository.count();

        assertThat(count).isGreaterThan(0);
    }

    @Test
    public void contextLoads()
    {
    }

}

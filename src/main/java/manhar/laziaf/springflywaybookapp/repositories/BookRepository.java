package manhar.laziaf.springflywaybookapp.repositories;

import manhar.laziaf.springflywaybookapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>
{
}

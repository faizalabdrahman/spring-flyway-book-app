package manhar.laziaf.springflywaybookapp.repositories;

import manhar.laziaf.springflywaybookapp.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>
{
}

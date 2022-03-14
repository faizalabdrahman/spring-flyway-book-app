package manhar.laziaf.springflywaybookapp.repositories;

import manhar.laziaf.springflywaybookapp.domain.BookNatural;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNaturalRepository extends JpaRepository<BookNatural, String>
{
}

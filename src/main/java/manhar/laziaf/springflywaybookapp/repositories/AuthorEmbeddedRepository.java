package manhar.laziaf.springflywaybookapp.repositories;

import manhar.laziaf.springflywaybookapp.domain.composite.AuthorEmbedded;
import manhar.laziaf.springflywaybookapp.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId>
{
}

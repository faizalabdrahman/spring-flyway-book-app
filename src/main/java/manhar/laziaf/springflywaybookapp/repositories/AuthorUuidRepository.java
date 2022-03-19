package manhar.laziaf.springflywaybookapp.repositories;

import manhar.laziaf.springflywaybookapp.domain.AuthorUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID>
{
}

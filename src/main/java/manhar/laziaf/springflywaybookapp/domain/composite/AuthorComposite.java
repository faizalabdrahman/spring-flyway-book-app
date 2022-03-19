package manhar.laziaf.springflywaybookapp.domain.composite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Entity
@IdClass(NameId.class)
public class AuthorComposite
{
    @Id
    private String firstName;

    @Id
    private String lastName;
    private String country;
}

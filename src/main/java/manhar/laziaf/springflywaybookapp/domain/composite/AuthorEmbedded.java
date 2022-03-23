package manhar.laziaf.springflywaybookapp.domain.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "author_composite")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEmbedded
{
    @EmbeddedId
    private NameId nameId;
    private String country;
}

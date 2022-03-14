package manhar.laziaf.springflywaybookapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookNatural
{
    @Id
    private String title;
    private String isbn;
    private String publisher;
}

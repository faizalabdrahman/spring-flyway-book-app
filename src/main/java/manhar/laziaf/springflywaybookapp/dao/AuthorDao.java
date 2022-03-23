package manhar.laziaf.springflywaybookapp.dao;

import manhar.laziaf.springflywaybookapp.domain.Author;

public interface AuthorDao
{
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}

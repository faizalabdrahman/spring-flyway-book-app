package manhar.laziaf.springflywaybookapp.dao;

import manhar.laziaf.springflywaybookapp.domain.Book;

public interface BookDao
{
    Book getById(Long id);
}

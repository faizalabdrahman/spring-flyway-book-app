package manhar.laziaf.springflywaybookapp.dao;

import manhar.laziaf.springflywaybookapp.domain.Book;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class BookDaoImpl implements BookDao
{
    private final DataSource dataSource;

    public BookDaoImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Book getById(Long id)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

    }
}

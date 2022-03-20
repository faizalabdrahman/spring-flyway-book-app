package manhar.laziaf.springflywaybookapp.dao;

import manhar.laziaf.springflywaybookapp.domain.Book;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

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

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if(resultSet.next())
            {
                return getBookFromResultSet(resultSet);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                closeAll(resultSet, ps, connection);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book findBookByTitle(String title)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM book WHERE title = ?");
            ps.setString(1, title);
            resultSet = ps.executeQuery();

            if(resultSet.next())
            {
                return getBookFromResultSet(resultSet);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                closeAll(resultSet, ps, connection);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book saveNewBook(Book book)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("INSERT INTO book (title, isbn, publisher, author_id) VALUES (?, ?, ?, ?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getPublisher());
            ps.setLong(4, book.getAuthorId());
            ps.execute();

            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");

            if(resultSet.next())
            {
                Long savedId = resultSet.getLong(1);

                return this.getById(savedId);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                closeAll(resultSet, ps, connection);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Book updateBook(Book book)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("UPDATE book SET title = ?, isbn = ?, publisher = ?, author_id = ? WHERE id = ?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getPublisher());
            ps.setLong(4, book.getAuthorId());
            ps.setLong(5, book.getId());
            ps.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                closeAll(resultSet, ps, connection);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        return this.getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("DELETE FROM book WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                closeAll(resultSet, ps, connection);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException
    {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setAuthorId(resultSet.getLong("author_id"));

        return book;
    }

    private void closeAll(ResultSet resultSet, PreparedStatement ps, Connection connection) throws SQLException
    {
        if(resultSet != null)
        {
            resultSet.close();
        }
        else if(ps != null)
        {
            ps.close();
        }
        else if(connection != null)
        {
            connection.close();
        }
    }
}

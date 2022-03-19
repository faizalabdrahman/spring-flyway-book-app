package manhar.laziaf.springflywaybookapp.dao;

import manhar.laziaf.springflywaybookapp.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao
{
    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Author getById(Long id)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author WHERE id = ?");
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if(resultSet.next())
            {
                return getAuthorFromResultSet(resultSet);
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
    public Author findAuthorByName(String firstName, String lastName)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author WHERE first_name = ? AND last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            resultSet = ps.executeQuery();

            if(resultSet.next())
            {
                return getAuthorFromResultSet(resultSet);
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
    public Author saveNewAuthor(Author author)
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("INSERT INTO author (first_name, last_name)  VALUES (?, ?)");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
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

        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException
    {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));

        return author;
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

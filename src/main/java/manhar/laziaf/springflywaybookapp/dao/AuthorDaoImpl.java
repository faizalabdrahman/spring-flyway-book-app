package manhar.laziaf.springflywaybookapp.dao;

import manhar.laziaf.springflywaybookapp.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);

            if(resultSet.next())
            {
                Author author = new Author();
                author.setId(id);
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));

                return author;
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
                if(resultSet != null)
                {
                    resultSet.close();
                }
                else if(statement != null)
                {
                    statement.close();
                }
                else if(connection != null)
                {
                    statement.close();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}

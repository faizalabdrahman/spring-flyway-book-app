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
                else if(ps != null)
                {
                    ps.close();
                }
                else if(connection != null)
                {
                    connection.close();
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

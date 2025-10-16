import java.sql.*;

public class Database {
    private Connection connection;
    private Statement statement;

    public Database() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_product", "root", ""
            );
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectQuery(String sql) {
        try {
            statement.executeQuery(sql);
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertUpdateDeleteQuery(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method ini akan mengembalikan 'true' jika ID sudah ada, dan 'false' jika belum.
    public boolean isIdExists(String id) {
        try (ResultSet resultSet = selectQuery("SELECT * FROM product WHERE id = '" + id + "'")) {
            // .next() akan return true jika query menghasilkan setidaknya satu baris data
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
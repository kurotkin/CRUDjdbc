import java.sql.*;

/**
 * Created by Vitaly Kurotkin on 09.11.2017.
 */
public class Program {
    static final String DATABASE_URL = "jdbc:mysql://localhost/BasicMySqlCRUD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "ab123";

    public static void main(String[] args) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Проблема с драйвером");
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM developers");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String fisrt_name = resultSet.getString("firs_name");
                String last_name = resultSet.getString("last_name");
                System.out.println(id + " | " + fisrt_name + " | " + last_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

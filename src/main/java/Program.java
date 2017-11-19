import java.sql.*;

/**
 * Created by Vitaly Kurotkin on 09.11.2017.
 */
public class Program {
    static final String DATABASE_URL = "jdbc:mysql://192.168.1.20/BasicMySqlCRUD?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "user";
    static final String PASSWORD = "ab12345678";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Проблема с драйвером");
            System.exit(1);
        }

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM developers");
            )

        {

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

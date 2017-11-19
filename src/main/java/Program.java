import dao.DeveloperDAO;
import dao.jdbc.JdbcDeveloperDAOImpl;
import model.Developer;

import java.sql.*;

/**
 * Created by Vitaly Kurotkin on 09.11.2017.
 */
public class Program {

    public static void main(String[] args) {
        DeveloperDAO devDAO = new JdbcDeveloperDAOImpl();
        try {
            Developer dev = devDAO.getById(1L);
            System.out.println(dev);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

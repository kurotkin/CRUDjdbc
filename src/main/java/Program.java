import dao.CompanyDAO;
import dao.DeveloperDAO;
import dao.jdbc.JdbcCompanyDAOImpl;
import dao.jdbc.JdbcDeveloperDAOImpl;
import model.Company;
import model.Developer;

import java.sql.*;
import java.util.List;

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

        CompanyDAO companyDAO = new JdbcCompanyDAOImpl();

        try {
            Company company = companyDAO.getById(1L);
            System.out.println(company);
            List<Company> companies = companyDAO.getAll();
            System.out.println("==================");
            for (Company company1 : companies) {
                System.out.println(company1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}

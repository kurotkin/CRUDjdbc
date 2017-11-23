import dao.CompanyDAO;
import dao.DeveloperDAO;
import dao.ProjectDAO;
import dao.jdbc.JdbcCompanyDAOImpl;
import dao.jdbc.JdbcDeveloperDAOImpl;
import dao.jdbc.JdbcProjectDAOImpl;
import model.Company;
import model.Developer;
import model.Project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitaly Kurotkin on 09.11.2017.
 */
public class Program {

    public static void main(String[] args) {
        DeveloperDAO devDAO = new JdbcDeveloperDAOImpl();
        CompanyDAO companyDAO = new JdbcCompanyDAOImpl();
        ProjectDAO projectDAO = new JdbcProjectDAOImpl();

        try {
            Set<Project> projects1 = new HashSet<>();
            projects1.add(new Project(100L, "New great project", new BigDecimal(999)));
            Developer dev1 = new Developer(100L, "Ivan", "Ivanov", "super user", new BigDecimal(4000));
            dev1.withProjects(projects1);





            Developer dev = devDAO.getById(1L);
            System.out.println(dev);
        } catch (SQLException e) {
            e.printStackTrace();
        }



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

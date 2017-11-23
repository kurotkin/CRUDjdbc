import dao.CompanyDAO;
import dao.DeveloperDAO;
import dao.ProjectDAO;
import dao.jdbc.JdbcCompanyDAOImpl;
import dao.jdbc.JdbcDeveloperDAOImpl;
import dao.jdbc.JdbcProjectDAOImpl;
import dao.utils.ConnectionUtil;
import model.Company;
import model.Developer;
import model.Project;
import model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitaly Kurotkin on 09.11.2017.
 */
public class Program {

    public static void main(String[] args) throws Exception {
        if (ConnectionUtil.USER.equals("") ||  ConnectionUtil.PASS.equals("")){
            throw new Exception("Введите имя пользователя и пароль в классе " + ConnectionUtil.class.getName());
        }

        DeveloperDAO devDAO = new JdbcDeveloperDAOImpl();
        CompanyDAO companyDAO = new JdbcCompanyDAOImpl();
        ProjectDAO projectDAO = new JdbcProjectDAOImpl();

        //Developer
        Developer dev1 = new Developer(100L, "Ivan", "Ivanov", "super user", new BigDecimal(4000));

        Set<Project> projects1 = new HashSet<>();
        projects1.add(new Project(50L, "New great project", new BigDecimal(999)));
        dev1.withProjects(projects1);

        Set<Skill> skills1 = new HashSet<>();
        skills1.add(new Skill(50L, "brain"));
        dev1.withSkills(skills1);

        //Save
        try {
            devDAO.save(dev1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load
        try {
            Developer devFromMysql = devDAO.getById(100L);
            System.out.println(devFromMysql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Load all
        try {
            List<Company> companies = companyDAO.getAll();
            companies.stream().forEach(company -> System.out.println(company));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Upload
        try {
            projectDAO.update(new Project(1L,"new project", new BigDecimal(245)));
            System.out.println(projectDAO.getById(1L));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Delete
        try {
            devDAO.delete(new Developer().withId(100L));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

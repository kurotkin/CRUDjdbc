package dao.jdbc;

import dao.CompanyDAO;
import dao.utils.ConnectionUtil;
import model.Company;
import model.Project;

import java.sql.*;
import java.sql.Connection;
import java.util.*;


/**
 * Created by Vitaly Kurotkin on 21.11.2017.
 */
public class JdbcCompanyDAOImpl implements CompanyDAO {
    @Override
    public Company getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM companies WHERE id =?");
        statement.setLong(1, id);
        statement.execute();
        ResultSet resultSet = statement.executeQuery();

        Company company = new Company();
        resultSet.next();
        company.withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"));

        statement = connection.prepareStatement("SELECT id, name, cost FROM projects " +
                "JOIN companies_projects ON companies_projects.companies_id = ? " +
                "WHERE id = companies_projects.projects_id");
        statement.setLong(1, id);
        resultSet = statement.executeQuery();

        Set<Project> projects = new HashSet<>();
        while (resultSet.next()) {
            Project project = new Project()
                    .withId(resultSet.getLong("id"))
                    .withName(resultSet.getString("name"))
                    .withCost(resultSet.getBigDecimal("cost"));
            projects.add(project);
        }
        company.withProjects(projects);

        resultSet.close();
        statement.close();
        connection.close();
        return company;
    }

    @Override
    public List<Company> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM companies");

        List<Company> companies = new ArrayList<>();
        while (resultSet.next()) {
            Company dev = new Company()
                    .withId(resultSet.getLong("id"))
                    .withName(resultSet.getString("name"));
            companies.add(dev);
        }


        PreparedStatement preStatement = connection.prepareStatement("SELECT id, name, cost FROM projects " +
                "JOIN companies_projects ON companies_projects.companies_id = ? " +
                "WHERE id = companies_projects.projects_id");

        for (int i = 0; i < companies.size(); i++){
            preStatement.setLong(1, companies.get(i).getId());
            preStatement.execute();
            ResultSet rs = preStatement.executeQuery();
            Set<Project> projects = new HashSet<>();
            while (rs.next()) {
                Project prj = new Project().withId(rs.getLong("id"))
                                .withName(rs.getString("name"))
                                .withCost(rs.getBigDecimal("cost"));
                projects.add(prj);
            }
            companies.get(i).withProjects(projects);
            rs.close();
        }

        resultSet.close();
        statement.close();
        connection.close();
        return companies;
    }

    @Override
    public void save(Company val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT into companies VALUES (?, ?)");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getName());
        statement.executeUpdate();

        statement = connection.prepareStatement("INSERT into projects VALUES (?, ?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, s.getId());
            statement.setString(2, s.getName());
            statement.setBigDecimal(3, s.getCost());
            statement.executeUpdate();
        }

        statement = connection.prepareStatement("INSERT into companies_projects VALUES (?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, val.getId());
            statement.setLong(2, s.getId());
            statement.executeUpdate();
        }

        statement.close();
        connection.close();
    }

    @Override
    public void update(Company val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE projects SET name = ? WHERE id = ?");
        statement.setLong(2, val.getId());
        statement.setString(1, val.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Company val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM companies_projects WHERE companies_id = ?");
        statement.setLong(1, val.getId());
        statement.executeUpdate();

        statement = connection.prepareStatement("DELETE FROM companies WHERE id = ?");
        statement.setLong(1, val.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}

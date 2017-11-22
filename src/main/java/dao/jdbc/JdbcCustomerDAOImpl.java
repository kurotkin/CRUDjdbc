package dao.jdbc;

import dao.CustomerDAO;
import dao.utils.ConnectionUtil;
import model.Customer;
import model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitaly Kurotkin on 21.11.2017.
 */
public class JdbcCustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE id =?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        Customer customer = new Customer();
        resultSet.next();
        customer.withId(resultSet.getLong("id"))
                .withFirstName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"));

        statement = connection.prepareStatement("SELECT id, name, cost FROM projects " +
                "JOIN customers_projects ON customers_projects.customers_id = ? " +
                "WHERE id = customers_projects.projects_id");
        statement.setLong(1, id);
        resultSet = statement.executeQuery();

        Set<Project> projects = new HashSet<>();
        while (resultSet.next()) {
            Project project = new Project().withId(resultSet.getLong("id"))
                    .withName(resultSet.getString("name"))
                    .withCost(resultSet.getBigDecimal("cost"));
            projects.add(project);
        }
        customer.withProjects(projects);

        resultSet.close();
        statement.close();
        connection.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            Customer cust = new Customer()
                    .withFirstName(resultSet.getString("first_name"))
                    .withLastName(resultSet.getString("last_name"));
            customers.add(cust);
        }

        PreparedStatement preStatement = connection.prepareStatement("SELECT id, name, cost FROM projects " +
                "JOIN customers_projects ON customers_projects.customers_id = ? " +
                "WHERE id = customers_projects.projects_id");

        for (int i = 0; i < customers.size(); i++){
            preStatement.setLong(1, customers.get(i).getId());
            ResultSet rs = preStatement.executeQuery();
            Set<Project> projects = new HashSet<>();
            while (rs.next()) {
                Project prj = new Project().withId(rs.getLong("id"))
                        .withName(rs.getString("name"))
                        .withCost(rs.getBigDecimal("cost"));
                projects.add(prj);
            }
            customers.get(i).withProjects(projects);
            rs.close();
        }

        resultSet.close();
        statement.close();
        connection.close();
        return customers;
    }

    @Override
    public void save(Customer val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT into customers VALUES (?, ?, ?)");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getFirstName());
        statement.setString(3, val.getLastName());
        ResultSet resultSet = statement.executeQuery();

        statement = connection.prepareStatement("INSERT into projects VALUES (?, ?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, s.getId());
            statement.setString(2, s.getName());
            statement.setBigDecimal(3, s.getCost());
            statement.executeQuery();
        }

        statement = connection.prepareStatement("INSERT into customers_projects VALUES (?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, val.getId());
            statement.setLong(2, s.getId());
            statement.executeQuery();
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Customer val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE customers SET first_name = ?, last_name = ? WHERE id = ?");
        statement.setLong(3, val.getId());
        statement.setString(2, val.getFirstName());
        statement.setString(1, val.getLastName());
        ResultSet resultSet = statement.executeQuery();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Customer val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE id = ?");
        statement.setLong(1, val.getId());
        ResultSet resultSet = statement.executeQuery();
        resultSet.close();
        statement.close();
        connection.close();
    }
}

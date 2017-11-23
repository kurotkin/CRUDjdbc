package dao.jdbc;

import dao.ProjectDAO;
import dao.utils.ConnectionUtil;
import model.Project;
import model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly Kurotkin on 20.11.2017.
 */
public class JdbcProjectDAOImpl implements ProjectDAO{
    @Override
    public Project getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM projects WHERE id = " + id);
        resultSet.next();
        Project project = new Project()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"))
                .withCost(resultSet.getBigDecimal("cost"));
        resultSet.close();
        statement.close();
        connection.close();
        return project;
    }

    @Override
    public List<Project> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM projects");

        List<Project> projects = new ArrayList<>();

        while (resultSet.next()) {
            Project project = new Project()
                    .withId(resultSet.getLong("id"))
                    .withName(resultSet.getString("name"))
                    .withCost(resultSet.getBigDecimal("cost"));
            projects.add(project);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return projects;
    }

    @Override
    public void save(Project val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT into projects VALUES (?, ?, ?)");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getName());
        statement.setBigDecimal(3, val.getCost());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Project val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE projects SET name = ?, cost = ? WHERE id = ?");
        statement.setLong(3, val.getId());
        statement.setBigDecimal(2, val.getCost());
        statement.setString(1, val.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Project val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM projects WHERE id = ? OR name = ?");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getName());
        ResultSet resultSet = statement.executeQuery();
        resultSet.close();
        statement.close();
        connection.close();
    }
}

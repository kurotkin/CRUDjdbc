package dao.jdbc;

import dao.DeveloperDAO;
import dao.utils.ConnectionUtil;
import model.Developer;
import model.Project;
import model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vitaly on 19.11.2017.
 */
public class JdbcDeveloperDAOImpl implements DeveloperDAO {

    private static final String sqlGetById = "SELECT * FROM developers " +
            "JOIN developer_skill ON developer_skill.developer_id = developers.id " +
            "JOIN skills ON skills.id = developer_skill.skill_id " +
            "JOIN developer_projects ON developer_projects.developer_id = developers.id " +
            "JOIN projects ON projects.id = developer_projects.projects_id " +
            "WHERE developers.id = ?";

    private static final String sqlGetDev = "SELECT * FROM developers";

    @Override
    public Developer getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlGetById);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        Developer developer = new Developer();
        HashSet<Skill> skills = new HashSet<>();
        HashSet<Project> projects = new HashSet<>();
        while (resultSet.next()) {
            developer.withId(resultSet.getLong("developers.id"))
                    .withFirstName(resultSet.getString("first_name"))
                    .withLastName(resultSet.getString("last_name"))
                    .withSpecialty(resultSet.getString("specialty"))
                    .withSalary(resultSet.getBigDecimal("salary"));

            skills.add(new Skill(
                    resultSet.getLong("skills.id"),
                    resultSet.getString("skills.name")));
            projects.add(new Project(
                    resultSet.getLong("projects.id"),
                    resultSet.getString("projects.name"),
                    resultSet.getBigDecimal("cost")));

        }
        developer.withSkills(skills).withProjects(projects);
        resultSet.close();
        statement.close();
        connection.close();
        return developer;
    }

    @Override
    public List<Developer> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlGetDev);

        List<Developer> developers = new ArrayList<>();
        HashSet<Skill> skills = new HashSet<>();
        HashSet<Project> projects = new HashSet<>();

        while (resultSet.next()) {
            Developer dev = new Developer()
                    .withId(resultSet.getLong("developers.id"))
                    .withFirstName(resultSet.getString("first_name"))
                    .withLastName(resultSet.getString("last_name"))
                    .withSpecialty(resultSet.getString("specialty"))
                    .withSalary(resultSet.getBigDecimal("salary"));
            developers.add(dev);
        }

        resultSet.close();
        statement.close();
        connection.close();
        return developers;
    }

    @Override
    public void save(Developer val) {

    }

    @Override
    public void update(Developer val) {

    }

    @Override
    public void delete(Developer val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM developers WHERE id = ?");
        statement.setLong(1, val.getId());
        ResultSet resultSet = statement.executeQuery();
        resultSet.close();
        statement.close();
        connection.close();
    }
}

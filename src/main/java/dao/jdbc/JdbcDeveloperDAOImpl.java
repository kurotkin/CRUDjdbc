package dao.jdbc;

import dao.DeveloperDAO;
import dao.utils.ConnectionUtil;
import model.Developer;
import model.Project;
import model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
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


    @Override
    public Developer getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlGetById);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        Developer developer = new Developer();
        Long developerId = 0L;
        String firstName = "";
        String lastName;
        String specialty;
        BigDecimal salary;
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
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public void save(Developer val) {

    }

    @Override
    public void update(Developer val) {

    }

    @Override
    public void delete(Developer val) {

    }
}

package dao.jdbc;

import dao.DeveloperDAO;
import dao.utils.ConnectionUtil;
import model.Developer;
import model.Project;
import model.Skill;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 * Created by Vitaly on 19.11.2017.
 */
public class JdbcDeveloperDAOImpl implements DeveloperDAO {

    @Override
    public Developer getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM developers " +
                "JOIN developer_skill ON developer_skill.developer_id = developers.id " +
                "JOIN skills ON skills.id = developer_skill.skill_id " +
                "JOIN developer_projects ON developer_projects.developer_id = developers.id " +
                "JOIN projects ON projects.id = developer_projects.projects_id " +
                "WHERE developers.id = ?");
        statement.setLong(1, id);
        statement.execute();
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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM developers");

        List<Developer> developers = new ArrayList<>();
        while (resultSet.next()) {
            Developer dev = new Developer()
                    .withId(resultSet.getLong("developers.id"))
                    .withFirstName(resultSet.getString("first_name"))
                    .withLastName(resultSet.getString("last_name"))
                    .withSpecialty(resultSet.getString("specialty"))
                    .withSalary(resultSet.getBigDecimal("salary"));
            developers.add(dev);
        }

        for (int i = 0; i < developers.size(); i++){
            PreparedStatement preStatement = connection.prepareStatement("SELECT id, name, cost FROM projects " +
                    "JOIN developer_projects ON developer_projects.developer_id = ? " +
                    "WHERE id = developer_projects.projects_id");
            preStatement.setLong(1, developers.get(i).getId());
            ResultSet rs = preStatement.executeQuery();
            Set<Project> projects = new HashSet<>();
            while (rs.next()) {
                Project prj = new Project().withId(rs.getLong("id"))
                        .withName(rs.getString("name"))
                        .withCost(rs.getBigDecimal("cost"));
                projects.add(prj);
            }
            developers.get(i).withProjects(projects);

            preStatement = connection.prepareStatement("SELECT id, name FROM skills " +
                    "JOIN developer_skill ON developer_skill.developer_id = ? " +
                    "WHERE id = developer_skill.skill_id");
            preStatement.setLong(1, developers.get(i).getId());
            rs = preStatement.executeQuery();
            Set<Skill> skills = new HashSet<>();
            while (rs.next()) {
                Skill skill = new Skill().withId(rs.getLong("id"))
                        .withName(rs.getString("name"));
                skills.add(skill);
            }
            developers.get(i).withSkills(skills);
            rs.close();
        }
        resultSet.close();
        statement.close();
        connection.close();
        return developers;
    }

    @Override
    public void save(Developer val) throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO developers VALUE (?, ?, ?, ?, ?)");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getFirstName());
        statement.setString(3, val.getLastName());
        statement.setString(4, val.getSpecialty());
        statement.setBigDecimal(5, val.getSalary());
        statement.executeUpdate();

        statement = connection.prepareStatement("INSERT into projects VALUES (?, ?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, s.getId());
            statement.setString(2, s.getName());
            statement.setBigDecimal(3, s.getCost());
            statement.executeUpdate();
        }

        statement = connection.prepareStatement("INSERT into developer_projects VALUES (?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, val.getId());
            statement.setLong(2, s.getId());
            statement.executeUpdate();
        }

        statement = connection.prepareStatement("INSERT into skills VALUES (?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, s.getId());
            statement.setString(2, s.getName());
            statement.executeUpdate();
        }

        statement = connection.prepareStatement("INSERT into developer_skill VALUES (?, ?)");
        for(Project s: val.getProjects()) {
            statement.setLong(1, val.getId());
            statement.setLong(2, s.getId());
            statement.executeUpdate();
        }

        statement.close();
        connection.close();
    }

    @Override
    public void update(Developer val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE developers SET first_name = ?, last_name = ?, specialty = ?, salary = ? WHERE id = ?");
        statement.setString(1, val.getFirstName());
        statement.setString(2, val.getLastName());
        statement.setString(3, val.getSpecialty());
        statement.setBigDecimal(4, val.getSalary());
        statement.setLong(5, val.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Developer val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM developer_projects WHERE developer_id = ?");
        statement.setLong(1, val.getId());
        statement.executeUpdate();

        statement = connection.prepareStatement("DELETE FROM developer_skill WHERE developer_id = ?");
        statement.setLong(1, val.getId());
        statement.executeUpdate();

        statement = connection.prepareStatement("DELETE FROM developers WHERE id = ?");
        statement.setLong(1, val.getId());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}

package dao.jdbc;

import dao.SkillDAO;
import dao.utils.ConnectionUtil;
import model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaly Kurotkin on 20.11.2017.
 */
public class JdbcSkillDAOImpl implements SkillDAO{

    @Override
    public Skill getById(Long id) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM skills WHERE id = " + id);
        resultSet.next();
        Skill skill = new Skill()
                .withId(resultSet.getLong("id"))
                .withName(resultSet.getString("name"));
        resultSet.close();
        statement.close();
        connection.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM skills");

        List<Skill> skills = new ArrayList<>();

        while (resultSet.next()) {
            Skill skill = new Skill()
                    .withId(resultSet.getLong("id"))
                    .withName(resultSet.getString("name"));
            skills.add(skill);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return skills;
    }

    @Override
    public void save(Skill val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT into skills VALUES (?, ?)");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void update(Skill val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE skills SET name = ? WHERE id = ?");
        statement.setLong(2, val.getId());
        statement.setString(1, val.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Skill val) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM skills WHERE id = ? OR name = ?");
        statement.setLong(1, val.getId());
        statement.setString(2, val.getName());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}

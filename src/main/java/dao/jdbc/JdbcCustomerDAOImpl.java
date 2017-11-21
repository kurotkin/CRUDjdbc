package dao.jdbc;

import dao.CustomerDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vitaly Kurotkin on 21.11.2017.
 */
public class JdbcCustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Customer val) throws SQLException {

    }

    @Override
    public void update(Customer val) throws SQLException {

    }

    @Override
    public void delete(Customer val) throws SQLException {

    }
}

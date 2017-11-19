package dao;

import java.util.List;

/**
 * Created by Vitaly on 19.11.2017.
 */
public interface GenericDAO<V, ID> {

    V getById(ID id);
    List<V> getAll();
    void save(V val);
    void update(V val);
    void delete(V val);
}

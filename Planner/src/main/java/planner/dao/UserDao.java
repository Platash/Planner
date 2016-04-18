package planner.dao;

/**
 * Created by Anna Platash on 4/15/16.
 */

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import planner.entity.User;


@Repository("userDao")
public class UserDao extends AbstractDao<Integer, User> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void persist(User entity) {
        getSession().persist(entity);
    }

    public void delete(User entity) {
        getSession().delete(entity);
    }

    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUserById(String id) {
        Query query = getSession().createSQLQuery("delete from User where id = :id");
        query.setString("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    public User getUserById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }
}

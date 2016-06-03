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

import planner.entity.UserData;


@Repository("userDao")
public class UserDao extends AbstractDao<Integer, UserData> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void addUser(UserData entity) {
        getSession().persist(entity);
    }

    public void delete(UserData entity) {
        getSession().delete(entity);
    }

    public void deleteUserById(String id) {
        Query query = getSession().createSQLQuery("delete from \"user\" where id = :id");
        query.setString("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<UserData> getAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<UserData>) criteria.list();
    }

    public UserData getUserById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (UserData) criteria.uniqueResult();
    }

    public UserData validateUser(String login, String password) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        criteria.add(Restrictions.eq("password", password));
        return (UserData) criteria.uniqueResult();
    }
}

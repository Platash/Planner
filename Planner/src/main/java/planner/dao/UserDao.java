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

    public int updateUser(UserData currentUser, UserData userData) {
        Query query = getSession().createSQLQuery("update \"user\" set " +
                "name=:name, " +
                "modification_date=:modificationDate, " +
                "password=:password " +
                "where id =:id");
        query.setInteger("id", currentUser.getId());
        query.setString("name", (userData.getName().isEmpty()?currentUser.getName():userData.getName()));
        query.setString("password", (userData.getPassword().isEmpty()?currentUser.getPassword():userData.getPassword()));
        query.setTimestamp("modificationDate", userData.getModificationDate());

        return query.executeUpdate();
    }

    public void delete(UserData entity) {
        getSession().delete(entity);
    }

    public int deleteUserById(Integer id) {
        Query query = getSession().createSQLQuery("delete from \"user\" where id = :id");
        query.setInteger("id", id);
        return query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<UserData> getAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<UserData>) criteria.list();
    }

    public UserData getUserById(Integer id) {
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

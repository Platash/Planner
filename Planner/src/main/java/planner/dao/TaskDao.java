package planner.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import planner.entity.TaskData;


import java.util.List;

/**
 * Created by Anna Platash on 5/8/16.
 */

@Repository("taskDao")
public class TaskDao extends AbstractDao<Integer, TaskData> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void addTask(TaskData entity) {
        getSession().persist(entity);
    }

    public void delete(TaskData entity) {
        getSession().delete(entity);
    }

    public void deleteTaskById(String id) {
        Query query = getSession().createSQLQuery("delete from task where id = :id");
        query.setString("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<TaskData> getAllTasks() {
        Criteria criteria = createEntityCriteria();
        return (List<TaskData>) criteria.list();
    }

    public TaskData getTaskById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (TaskData) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<TaskData> getTasksByUser(String owner_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("owner_id", owner_id));
        return (List<TaskData>) criteria.list();
    }
}


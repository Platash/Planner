package planner.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import planner.entity.TaskTagData;

/**
 * Created by Anna Platash on 5/21/16.
 */

@Repository("taskTagDao")
public class TaskTagDao extends AbstractDao<Integer, TaskTagData> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Integer addTaskTag(TaskTagData entity) {
        getSession().persist(entity);
        getSession().flush();
        return entity.getTaskId();
    }
}
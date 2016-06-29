package planner.dao;

import org.hibernate.Query;
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

    public void deleteByTaskId(Integer id) {
        try {
            Query query = getSession().createSQLQuery("delete from task_tag where task_id = :id");
            query.setInteger("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
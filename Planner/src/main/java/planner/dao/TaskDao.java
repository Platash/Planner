package planner.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import planner.entity.TaskData;
import planner.exception.BadSQLException;

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

    public Integer addTask(TaskData entity) {
        getSession().persist(entity);
        getSession().flush();
        return entity.getId();
    }

    public void updateTask(TaskData taskData) {
        Query query = getSession().createSQLQuery("update task set " +
                "title=:title, " +
                "modification_date=:modificationDate, " +
                "start_date=:start_date, " +
                "end_date=:end_date, " +
                "location=:location, " +
                "description=:description " +
                "where id =:id");
        query.setInteger("id", taskData.getId());
        query.setString("title", taskData.getTitle());
        query.setTimestamp("start_date", taskData.getStart());
        query.setTimestamp("end_date", taskData.getEnd());
        query.setString("location", taskData.getLocation());
        query.setString("description", taskData.getDescription());
        query.setTimestamp("modificationDate", taskData.getModificationDate());

        query.executeUpdate();
    }

    public void delete(TaskData entity) {
        getSession().delete(entity);
    }

    public void deleteTaskById(Integer id) throws BadSQLException {
        try {
            Query query = getSession().createSQLQuery("delete from task where id = :id");
            query.setInteger("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadSQLException("Error while trying to delete task, id:" + id);
        }

    }

    @SuppressWarnings("unchecked")
    public List<TaskData> getAllTasks() {
        Criteria criteria = createEntityCriteria();
        return (List<TaskData>) criteria.list();
    }

    public TaskData getTaskById(Integer id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (TaskData) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<TaskData> getTasksByUser(Integer owner_id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("owner_id", owner_id));
        return (List<TaskData>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<TaskData> getTasksFromInterval(String start, String end) {
        String start_ = "\'" + start + "\'";
        String end_ = "\'" + end + "\'";
        Query query = getSession().createSQLQuery("select * from task where start_date <= " + end_ +
                " AND end_date >= " + start_).addEntity(TaskData.class);

        return query.list();
    }

    public List<TaskData> getTasksFromIntervalByUserId(String start, String end, Integer id) {
        String start_ = "\'" + start + "\'";
        String end_ = "\'" + end + "\'";
        Query query = getSession().createSQLQuery("select * from task where start_date <= " + end_ +
                " AND end_date >= " + start_ + " AND owner_id = " + id).addEntity(TaskData.class);

        return query.list();
    }
}


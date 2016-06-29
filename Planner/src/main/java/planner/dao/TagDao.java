package planner.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import planner.entity.TagData;

import java.util.List;

/**
 * Created by Anna Platash on 5/21/16.
 */

@Repository("tagDao")
public class TagDao extends AbstractDao<Integer, TagData> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void addTag(TagData entity) {
        getSession().persist(entity);
        getSession().flush();

    }

    public void delete(TagData entity) {
        getSession().delete(entity);
    }

    public void deleteTagByName(String name) {
        Query query = getSession().createSQLQuery("delete from tag where name = :name");
        query.setString("name", name);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<TagData> getAllTags() {
        Criteria criteria = createEntityCriteria();
        return (List<TagData>) criteria.list();
    }

    public TagData getTagByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (TagData) criteria.uniqueResult();
    }

}

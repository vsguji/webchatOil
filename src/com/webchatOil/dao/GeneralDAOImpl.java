package com.webchatOil.dao;  
  
import java.io.Serializable;  
import java.util.List;  
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.orm.hibernate4.HibernateTemplate;  
import org.springframework.stereotype.Repository;  
  
//DAO的实现类
@Repository("GeneralDAOImpl")
public class GeneralDAOImpl implements GeneralDao {  
  private static final Logger logger = Logger.getLogger(GeneralDAOImpl.class); 
   // @Autowired  
    private HibernateTemplate hibernateTemplate;  
    	 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
       this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    public <T> T findById(Class<T> type, Serializable id) {  
        return hibernateTemplate.get(type, id);  
    }  
  
    public <T> List<T> findAll(Class<T> type) { 
       System.out.println("findAll");
        return hibernateTemplate.loadAll(type);  
    }  
  
    public void save(Object... entities) {  
        for (Object entity : entities) {  
            hibernateTemplate.save(entity);  
        }  
    }  
  
    public void saveOrUpdate(Object entity) {  
        hibernateTemplate.saveOrUpdate(entity);  
    }  
  
    public void update(Object... entities) {  
        for (Object entity : entities) {  
            hibernateTemplate.update(entity);  
        }  
    }  
  
    public void delete(Object... entities) {  
        for (Object entity : entities) {  
            if (entity != null) {  
                hibernateTemplate.delete(entity);  
            }  
        }  
    }  
  
    public void deleteById(Class<?> type, Serializable id) {  
        if (id == null) {  
            return;  
        }  
        Object entity = findById(type, id);  
        if (entity == null) {  
            return;  
        }  
        delete(entity);  
    }  
  
    public void refresh(Object... entities) {  
        for (Object entity : entities) {  
            hibernateTemplate.refresh(entity);  
        }  
    }  
  
    public void flush() {  
        hibernateTemplate.flush();  
    }  
}  
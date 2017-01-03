package com.webchatOil.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

//数据存储层通用DAO接口定义
@Repository
public interface GeneralDao {

	 public <T> T findById(Class<T> type, Serializable id);  
	  
	    public <T> List<T> findAll(Class<T> type);  
	  
	    public void save(Object... entities);  
	  
	    public void update(Object... entities);  
	  
	    public void saveOrUpdate(Object entity);  
	  
	    public void delete(Object... entities);  
	  
	    public void deleteById(Class<?> type, Serializable id);  
	  
	    public void refresh(Object... entities);  
	  
	    public void flush();  
}

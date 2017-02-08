package com.webchatOil.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webchatOil.dao.LKUserListDao;
import com.webchatOil.model.LKUserinfo;

@Repository
@Transactional
public class LKUserListDaoImpl extends HibernateDaoSupport implements LKUserListDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	/**
	 * 获取全部客户列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getAllUsers(String hql, int offset, int length) {
		// TODO Auto-generated method stub
		return (List) hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = (Query) session.createQuery(hql);
				((org.hibernate.Query) query).setFirstResult(offset);
				((org.hibernate.Query) query).setMaxResults(length);
				List list = ((org.hibernate.Query) query).list();
				return list;
			}
		});
	}	

	/**
	 * 获取单个客户
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getSingleUserInfo(String hql, String userID) {
		// TODO Auto-generated method stub
		List list = (List)hibernateTemplate.find(hql, userID);
		return list;
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find(hql).size();
	}

	
	
	/**
	 * 新客户
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean insertNewUser(LKUserinfo user) {
		
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
						hibernateTemplate.execute(new HibernateCallback() {
							public Object doInHibernate(Session session) throws HibernateException {
								session.save(user);
								return true;
							}
						});
						return false;
	}
	
	/**
	 * 更新客户信息
	 * @return 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean updateNewUser(LKUserinfo user){
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				hibernateTemplate.execute(new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						session.update(user);
						return true;
					}
				});
				return false;
	}
	
	/**
	 * 删除客户信息
	 * @return 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean deleteNewUser(LKUserinfo user){
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				hibernateTemplate.execute(new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						session.delete(user);
						return true;
					}
				});
				return false;
	}	
}

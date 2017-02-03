package com.webchatOil.dao.Impl;

import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import com.webchatOil.dao.LKUserListDao;
import com.webchatOil.model.LKUserinfo;

public class LKUserListDaoImpl extends HibernateDaoSupport implements LKUserListDao {

	@Resource HibernateTemplate ht;
	
	/**
	 * 获取全部客户列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getAllUsers(String hql, int offset, int length) {
		// TODO Auto-generated method stub
		return (List) ht.execute(new HibernateCallback() {
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
		List list = (List)ht.find(hql, userID);
		return list;
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		return ht.find(hql).size();
	}

	@Override
	public boolean newCourse(LKUserinfo course) {
		// TODO Auto-generated method stub
		return false;
	}

}

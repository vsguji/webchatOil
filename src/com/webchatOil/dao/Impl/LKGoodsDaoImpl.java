package com.webchatOil.dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webchatOil.dao.LKGoodsDao;
import com.webchatOil.model.LKGoodsInfo;
import com.webchatOil.model.LKGoodsPic;

@Repository
@Transactional
public class LKGoodsDaoImpl extends HibernateDaoSupport implements LKGoodsDao{

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getAllGoods(String hql, int offset, int length) {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getSingleGoodInfo(String hql, String goodID) {
		// TODO Auto-generated method stub
		return (List)hibernateTemplate.find(hql, goodID);
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find(hql).size();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean insertNewGood(LKGoodsInfo good) {
		// TODO Auto-generated method stub
		return (boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				session.save(good);
				return true;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean updateNewGood(LKGoodsInfo good) {
		// TODO Auto-generated method stub
		return (boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				session.update(good);
				return true;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean deleteNewGood(LKGoodsInfo good) {
		// TODO Auto-generated method stub
		return (boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				session.delete(good);
				return true;
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean insertGoodsPic(LKGoodsPic goodPic) {
		// TODO Auto-generated method stub
		return (boolean) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				session.save(goodPic);
				return true;
			}
		});
	}
}

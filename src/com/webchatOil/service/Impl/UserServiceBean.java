package com.webchatOil.service.Impl;
import java.util.List;

import com.webchatOil.dao.LKUserListDao;
import com.webchatOil.model.LKUserinfo;
import com.webchatOil.po.page.PageBean;
import com.webchatOil.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceBean implements UserService{

	@Autowired
	private LKUserListDao userDao;
	
	@Override
	@Transactional
	public PageBean getAllUsersInfo(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		final String hql = "from LKUserinfo";
		int allRow = userDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		final int page = PageBean.countCurrentPage(currentPage);
		List list = userDao.getAllUsers(hql, offset, length);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(page);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getSingleUserInfo(String userID) {
		// TODO Auto-generated method stub
		final String hql = "from LKUserinfo where wechatID = ?";
		return userDao.getSingleUserInfo(hql, userID);
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean insertNewUser(LKUserinfo user) {
		// TODO Auto-generated method stub
		boolean status = userDao.insertNewUser(user);
		
		return status;
	}

	@Override
	public boolean updateNewUser(LKUserinfo user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNewUser(LKUserinfo user) {
		// TODO Auto-generated method stub
		return false;
	}
}

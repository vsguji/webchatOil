package com.webchatOil.service.Impl;

import java.util.List;

import javax.annotation.Resource;
import com.webchatOil.dao.LKUserListDao;
import com.webchatOil.model.LKUserinfo;
import com.webchatOil.po.page.PageBean;
import com.webchatOil.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=false)
public class UserServiceBean implements UserService{

	@Resource private LKUserListDao courseDao;
	
	@Override
	public PageBean getAllUsersInfo(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		final String hql = "from LKUserinfo c order by c.E_id";
		int allRow = courseDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		final int page = PageBean.countCurrentPage(currentPage);
		
		List list = courseDao.getAllUsers(hql, offset, length);
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(page);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	@Override
	public List getSingleUserInfo(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean newCourse(LKUserinfo course) {
		// TODO Auto-generated method stub
		return false;
	}

}

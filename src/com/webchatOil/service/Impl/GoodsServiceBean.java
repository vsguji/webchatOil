package com.webchatOil.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webchatOil.dao.LKGoodsDao;
import com.webchatOil.model.LKGoodsInfo;
import com.webchatOil.model.LKGoodsPic;
import com.webchatOil.model.LKUserinfo;
import com.webchatOil.po.page.PageBean;
import com.webchatOil.service.GoodsService;
import com.webchatOil.service.UserService;

@Service("goodsService")
public class GoodsServiceBean implements GoodsService{
	
	@Autowired
	private LKGoodsDao goodsDao;
	
	@Override
	@Transactional
	public PageBean getAllGoodsInfo(int pageSize, int currentPage) {
		
		// TODO Auto-generated method stub
		final String hql = "from LKGoodsInfo";
		int allRow = goodsDao.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		final int page = PageBean.countCurrentPage(currentPage);
		List list = goodsDao.getAllGoods(hql, offset, length);
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
	public List getSingleGooderInfo(String byTime) {
		// TODO Auto-generated method stub
		final String hql = "from LKGoodsPic t where t.datetime = ?";
		return goodsDao.getSingleGoodInfo(hql, byTime);
	}

	@Override
	public int getAllRowCount(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean insertNewGoods(LKGoodsInfo good) {
		// TODO Auto-generated method stub
		return goodsDao.insertNewGood(good);
	}

	@Override
	public boolean updateNewGoods(LKGoodsInfo good) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNewGoods(LKGoodsInfo good) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertGoodsPic(LKGoodsPic goodPic) {
		// TODO Auto-generated method stub
		return goodsDao.insertGoodsPic(goodPic);
	}

}

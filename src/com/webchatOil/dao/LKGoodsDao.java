package com.webchatOil.dao;

import java.util.List;

import com.webchatOil.model.LKGoodsInfo;
import com.webchatOil.model.LKGoodsPic;

public interface LKGoodsDao {
	/**
	 * 获取所有商品
	 * @return
	 */
	public List getAllGoods(final String hql,final int offset,final int length);
	
	
	/**
	 * 根据课程代码获取单个商品信息
	 * @return 
	 */
	public List getSingleGoodInfo(String hql,String byTime);
	
	/**
	 * 获取总记录数
	 * 
	 * @param hql
	 * 			查询条件
	 * @return	总记录数
	 */
	public int getAllRowCount(String hql);
	
	
	/**
	 * 新商品
	 * @return
	 */
	public boolean insertNewGood(LKGoodsInfo good);
	
	/**
	 * 更新商品信息
	 * @return 
	 */
	public boolean updateNewGood(LKGoodsInfo good);
	
	/**
	 * 删除商品信息
	 * @return 
	 */
	public boolean deleteNewGood(LKGoodsInfo good);
	
	
	/**
	 * 上传商品图片
	 * return 
	 */
	public boolean insertGoodsPic(final LKGoodsPic goodPic);
}

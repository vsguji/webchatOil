package com.webchatOil.service;

import java.util.List;

import com.webchatOil.model.LKGoodsInfo;
import com.webchatOil.model.LKGoodsPic;
import com.webchatOil.po.page.PageBean;

public interface GoodsService {

	/**
	 * 获取所有商品分页信息
	 * @return
	 */
	public PageBean getAllGoodsInfo(int pageSize,int currentPage);
	
	/**
	 * 根据单个商品信息
	 * @return 
	 */
	public List getSingleGooderInfo(String byTime);
	
	/**
	 * 获取总记录数
	 * 
	 * @param hql
	 * 			查询条件
	 * @return	总记录数
	 */
	public int getAllRowCount(String hql);
	
	/**
	 * 新增商品
	 * @param 商品
	 * 			新获取信息实体类
	 * @return
	 */
	public boolean insertNewGoods(final LKGoodsInfo good);
	
	/**
	 * 更新商品获取信息
	 * @param  获取 商品 
	 *        更新客户信息实体类
	 * @return 
	 */
	public boolean updateNewGoods(final LKGoodsInfo good);
	
	/**
	 * 删除商品信息
	 * @param 商品
	 *        删除商品信息实体类
	 * @return 
	 */
	public boolean deleteNewGoods(final LKGoodsInfo good);
	
	
	/**
	 * 上传商品图片
	 * return 
	 */
	public boolean insertGoodsPic(final LKGoodsPic goodPic);
}

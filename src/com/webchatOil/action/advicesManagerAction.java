package com.webchatOil.action;
import java.util.ArrayList;
import java.util.List;

public class advicesManagerAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	/**
	 * 用户提交建议
	 */
	public void doCommitAdvice(){
		
	}

	/**
	 * 用户建议模糊搜索
	 */
	public List<String> doFoundAdvices(String key){
		return null;
	}
	
	/**
	 *  获取用户留言,默认20条
	 */
	public List<String> doAdvicesNextPage(int next){
		return null;
	}
	
	/**
	 *  获取用户留言,默认20条
	 */
	public List<String> doAdvicesPrePage(int previous){
		return null;	
	}
	
	/**
	 * 辅助用户提交信息
	 */
	public doHelp(){
		
	}
}

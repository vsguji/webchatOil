package com.webchatOil.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.thoughtworks.xstream.core.util.Pool;
import com.webchatOil.model.LKGoodsInfo;
import com.webchatOil.model.LKGoodsPic;
import com.webchatOil.po.page.PageBean;
import com.webchatOil.service.GoodsService;
import com.webchatOil.util.RequestTypeAnnotation;
import com.webchatOil.util.RequestTypeAnnotation.RequestType;

/*
 * 商品管理类
 */
public class GoodsManagerAction extends BaseAction{

	private static final long serialVersionUID = 4321845277376234101L;
	HttpServletRequest request = getRequest();
	HttpServletResponse response = getResponse();
	private Logger logger = Logger.getLogger(activtiyAction.class);
	
	@Autowired
    private GoodsService goodsService;
	private PageBean pageBean;
	private String message;
		
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	public GoodsManagerAction() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 添加新商品
	 */
	@RequestTypeAnnotation(RequestType.POST)
	public void insertGoods() throws UnsupportedEncodingException, IOException{
		 Gson rspGson = new Gson();
		 Map<String, Object> rspMap = new HashMap<String, Object>();
		 // 接受body数据：
		 InputStreamReader inputReader = new InputStreamReader( 
				 request.getInputStream(), "UTF-8"); 
		 BufferedReader bufferReader = new BufferedReader(inputReader); 
		 StringBuilder sb = new StringBuilder(); 
		 String line = null; 
		 while ((line = bufferReader.readLine()) != null) { 
		 sb.append(line); 
		 } 
		 
		 @SuppressWarnings("unchecked")
		 Map<String, Object> toJson = rspGson.fromJson(sb.toString(), Map.class);
		 System.out.println("obj0 " + sb.toString());
		 System.out.println("obj " + toJson);
		 logger.log(Priority.DEBUG, toJson);
		try {
			LKGoodsInfo newGood = rspGson.fromJson(rspGson.toJson(toJson), LKGoodsInfo.class);
			if (goodsService.insertNewGoods(newGood)){
			    message = "发布成功！";
			     rspMap.put("status", 200);
			     rspMap.put("content", "ok");
			     getResponse().getWriter().write(rspGson.toJson(rspMap));
			    }
		    else{
		    	 rspMap.put("status", 400);
			     rspMap.put("content", "fail");
		    	 message = "发布失败！";
		    	 getResponse().getWriter().write(rspGson.toJson(rspMap));
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * 更新商品
	 */
    public void updateGoods(LKGoodsInfo obj){
		
	}
	
    /* 
   	 * 获取商品列表
   	 */
    @RequestTypeAnnotation(RequestType.GET)
  	public void getGoodsList(){
  		Gson json = new Gson();
  		int size = Integer.parseInt(request.getParameter("size"));
  		int index = Integer.parseInt(request.getParameter("index"));
  		PageBean currentPageBean  = goodsService.getAllGoodsInfo(size, index);
  		List tList =  currentPageBean.getList();
  		List<String> thumbPic =  new ArrayList();
  		for (int i = 0; i < tList.size(); i++) {
  	       	LKGoodsInfo goodsInfo = (LKGoodsInfo) tList.get(i);
  	        String time = goodsInfo.getG_time(); 
  	         if (time == null){	
  	        	 time = "";
	        }
  	          LKGoodsPic goodPic = (LKGoodsPic)goodsService.getSingleGooderInfo(time).get(0);
  	          if (goodPic != null){
  	        	  thumbPic.add(i, goodPic.getPicPaths());
  	          }
  	     }
  		JSONArray tArray = new JSONArray(tList);
  		Map< String, Object> jsonTree = new HashMap<String, Object>();
  		jsonTree.put("list", tArray);
  		jsonTree.put("thumbs", thumbPic);
  		jsonTree.put("rows", currentPageBean.getAllRow());
  		String jsonString =  json.toJson(jsonTree);
  	      try {
  			getResponse().getWriter().write(jsonString);
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		System.out.println("goto json :" + json.toJson(tList));		
  	}
	/*
	 * 删除商品 
	 */
	public void deleteGoods(LKGoodsInfo obj){
		
	}
	
	/*
	 * 上传商品图片
	 */
	 @RequestTypeAnnotation(RequestType.POST)
	public void insertGoodsPic() throws UnsupportedEncodingException, IOException{
		
		 Gson rspGson = new Gson();
		 Map<String, Object> rspMap = new HashMap<String, Object>();
		 // 接受body数据：
		 InputStreamReader inputReader = new InputStreamReader( 
				 request.getInputStream(), "UTF-8"); 
		 BufferedReader bufferReader = new BufferedReader(inputReader); 
		 StringBuilder sb = new StringBuilder(); 
		 String line = null; 
		 while ((line = bufferReader.readLine()) != null) { 
		 sb.append(line); 
		 } 
		 
		 @SuppressWarnings("unchecked")
		 Map<String, Object> toJson = rspGson.fromJson(sb.toString(), Map.class);
		 System.out.println("obj0 " + sb.toString());
		 System.out.println("obj " + toJson);
		 logger.log(Priority.DEBUG, toJson);
		try {
			LKGoodsPic newGoodPic = rspGson.fromJson(rspGson.toJson(toJson), LKGoodsPic.class);
			if (goodsService.insertGoodsPic(newGoodPic)){
			    message = "发布成功！";
			     rspMap.put("status", 200);
			     rspMap.put("content", "ok");
			     getResponse().getWriter().write(rspGson.toJson(rspMap));
			    }
		    else{
		    	 rspMap.put("status", 400);
			     rspMap.put("content", "fail");
		    	 message = "发布失败！";
		    	 getResponse().getWriter().write(rspGson.toJson(rspMap));
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

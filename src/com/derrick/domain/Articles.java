package com.derrick.domain;

import java.io.Serializable;
/**
 * 
* @ClassName: Articles 
* @Description: TODO(多图文消息) 
* @author derrick_hoh@163.com  
* @date 2015年1月21日 下午2:43:32 
*
 */
public class Articles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;

	public Articles() {
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}

package com.webchatOil.model;
import java.util.Date;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Userinfo entity.
 * 员工信息
 */
@Entity							// 表示为实体类
@Table(name="tb_userinfo",schema ="wechatdb")		// 表名注解
public class LKUserinfo implements Serializable {
	private static final long serialVersionUID = -1496732180293413262L;
	// Field
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int  E_id; // 员工编号
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int  D_id; // 部门编号
	@Column(unique=true)
	private String E_name; // 员工姓名
	private String loginname; // 登录名
	private String pwd; // 密码 
	private String sex;  // 性别
	private Date birthday; // 生日
	private String phoneH; // 家庭电话
	private String phoneM; // 手机号
	private String Address; // 家庭住址
    private String popedom; // 权限
    @Column(unique=true,nullable = false)
    private String wechatID; // 微信id
    private String wechatPic;// 客户微信头像
    // Property accessors

	public int getE_id() {
		return this.E_id;
	}

	public void setE_id(int eid) {
		this.E_id = eid;
	}
	
	public int getD_id() {
		return this.D_id;
	}
	
	public void setD_id(int did) {
		this.D_id = did;
	}
	
	public String getE_name() {
		return this.E_name;
	}
	
	public void setE_name(String name) {
		this.E_name = name;
	}
	
	public String getLoginname() {
		return this.loginname;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getSex() {
		return this.sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getPhoneH() {
		return this.phoneH;
	}
	
	public void setPhoneH(String phone) {
		this.phoneH = phone;
	}
	
	public String getphoneM() {
		return this.phoneM;
	}
	
	public void setPhoneM(String phoneM) {
		this.phoneM = phoneM;
	}
	
	public String getAddress() {
		return this.Address;
	}
	
	public void setAddress(String address) {
		this.Address = address;
	}
	
	public String getPopedom() {
		return this.popedom;
	}
	
	public void setPopedom(String popeDom) {
		this.popedom = popeDom;
	}
	
	public String getWechatID() {
		return this.wechatID;
	}
	
	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}
	
	public String getWechatPic() {
		return this.wechatPic;
	}
	
	public void setWechatPic(String wechatPic) {
		this.wechatPic = wechatPic;
	}
}
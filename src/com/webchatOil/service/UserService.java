package com.webchatOil.service;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;  
 
import com.webchatOil.dao.GeneralDao;  
import com.webchatOil.model.LKUserinfo; 
  
//处理逻辑的Service：UserService.java 
//因为Service只是处理增删改查，没有验证数据，所以比较简单，值得注意的是，@Transactional事务注解只能写在Service层，
//不能写在Action层，不然会出错，估计是对Spring3对Struts2支持的不太好 

@Service("UserService")
@Transactional
public class UserService {  
  
    @Autowired  
    private GeneralDao generalDao;  
  
    @Transactional
    public LKUserinfo findByUserId(String userId) {  
        return generalDao.findById(LKUserinfo.class, userId);  
    }  
  
    @Transactional
    public List<LKUserinfo> findAll() { 
    	System.out.println("findAll List<LKUserinfo>");
        return generalDao.findAll(LKUserinfo.class);  
    }  
  
    @Transactional  
    public void createUser(LKUserinfo user) {  
        generalDao.save(user);  
    }  
  
    @Transactional  
    public void updateUser(LKUserinfo user) { 
        generalDao.update(user);  
    }  
  
    @Transactional  
    public void deleteUser(int  userId) {  
        generalDao.deleteById(LKUserinfo.class, userId);  
    }  
  
    @Transactional  
    public void deleteUser(LKUserinfo user) {  
        generalDao.delete(user);  
    } 
    
    @Transactional
    public void ttMethod(){
    	System.out.println("ttMethod");
    	List<LKUserinfo> users = findAll();
    }
}  
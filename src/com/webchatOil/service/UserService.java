package com.webchatOil.service;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;  
  

import com.webchatOil.dao.GeneralDao;  
import com.webchatOil.model.Userinfo; 
  
//处理逻辑的Service：UserService.java 
//因为Service只是处理增删改查，没有验证数据，所以比较简单，值得注意的是，@Transactional事务注解只能写在Service层，
//不能写在Action层，不然会出错，估计是对Spring3对Struts2支持的不太好 

@Service  
public class UserService {  
  
    @Autowired  
    @Qualifier("GeneralDao")
    private GeneralDao generalDao;  
  
    public Userinfo findByUserId(String userId) {  
        return generalDao.findById(Userinfo.class, userId);  
    }  
  
    public List<Userinfo> findAll() {  
        return generalDao.findAll(Userinfo.class);  
    }  
  
    @Transactional  
    public void createUser(Userinfo user) {  
        generalDao.save(user);  
    }  
  
    @Transactional  
    public void updateUser(Userinfo user) {  
        generalDao.update(user);  
    }  
  
    @Transactional  
    public void deleteUser(String userId) {  
        generalDao.deleteById(Userinfo.class, userId);  
    }  
  
    @Transactional  
    public void deleteUser(Userinfo user) {  
        generalDao.delete(user);  
    }  
}  
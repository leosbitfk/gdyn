package com.gdyn.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
  





import com.gdyn.user.DAO.UserinfoDAO;
import com.gdyn.user.userinfo.Userinfo;
  
@Service("UserService")  
public class UserService  {  
    @Autowired
    private UserinfoDAO userDao;  
    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    public Userinfo getUserById(String userId) {  
        // TODO Auto-generated method stub  
        return  this.userDao.selectByPrimaryKey(userId);  
    }  
     /**
      * 根据id更新用户信息
      */
    public void updateUserinfo(Userinfo user){
    	int a=userDao.updateByPrimaryKey(user);
    }
    /**
     * 获取所有用户信息
     */
    public List<Userinfo> queryAlluserinfo(){
    	return this.userDao.selectAllUser();
    }
}  

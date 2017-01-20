package com.gdyn.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
  








import com.gdyn.user.DAO.UserinfoDAO;
import com.gdyn.user.roleDAO.UserRoleDAO;
import com.gdyn.user.userRole.UserRole;
import com.gdyn.user.userinfo.Userinfo;
import com.gdyn.util.MD5;
  /**
   * 此service处理关于用户信息的所有业务
   * @author leo
   *
   */
@Service("UserService")  
public class UserService  {  
    @Autowired
    private UserinfoDAO userDao;  
    @Autowired
    private UserRoleDAO roleDAO;
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
//    public void updateUserinfo(Userinfo user){
//    	int a=userDao.updateByPrimaryKey(user);
//    }
    public void updateUserinfo(Userinfo user){
    	int a=userDao.updateByPrimaryKeySelective(user);
    }
    /**
     * 获取所有用户信息
     */
    public List<Userinfo> queryAlluserinfo(){
    	return this.userDao.selectAllUser();
    }
    /**
     * 根据用户id更改password
     */
    public void updatePassword(Userinfo record){
    	if(record.getPassword()!=null){
    	record.setPassword(MD5.getMd5(record.getPassword()));
    	}
    	int a=userDao.updateByPrimaryKeySelective(record);
    }
    /**
     * 根据id取权限
     */
    public UserRole getUserRoleById(String id){
    	return this.roleDAO.selectByPrimaryKey(id);
    }
    /**
     * 获取所有用户权限
     */
    public List<UserRole> getAllRole(){
    	return this.roleDAO.selectAllRole();
    }
    /**
     * 根据id更改用户权限
     */
    public void updateRole(UserRole role){
    	int a=this.roleDAO.updateByPrimaryKey(role);
    }
}  

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
   * ��service��������û���Ϣ������ҵ��
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
     * ����id��ȡ�û�
     * @param userId
     * @return
     */
    public Userinfo getUserById(String userId) {  
        // TODO Auto-generated method stub  
        return  this.userDao.selectByPrimaryKey(userId);  
    }  
     /**
      * ����id�����û���Ϣ
      */
//    public void updateUserinfo(Userinfo user){
//    	int a=userDao.updateByPrimaryKey(user);
//    }
    public void updateUserinfo(Userinfo user){
    	int a=userDao.updateByPrimaryKeySelective(user);
    }
    /**
     * ��ȡ�����û���Ϣ
     */
    public List<Userinfo> queryAlluserinfo(){
    	return this.userDao.selectAllUser();
    }
    /**
     * �����û�id����password
     */
    public void updatePassword(Userinfo record){
    	if(record.getPassword()!=null){
    	record.setPassword(MD5.getMd5(record.getPassword()));
    	}
    	int a=userDao.updateByPrimaryKeySelective(record);
    }
    /**
     * ����idȡȨ��
     */
    public UserRole getUserRoleById(String id){
    	return this.roleDAO.selectByPrimaryKey(id);
    }
    /**
     * ��ȡ�����û�Ȩ��
     */
    public List<UserRole> getAllRole(){
    	return this.roleDAO.selectAllRole();
    }
    /**
     * ����id�����û�Ȩ��
     */
    public void updateRole(UserRole role){
    	int a=this.roleDAO.updateByPrimaryKey(role);
    }
}  

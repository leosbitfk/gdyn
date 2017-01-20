package com.gdyn.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
  






import com.gdyn.user.DAO.UserinfoDAO;
import com.gdyn.user.userinfo.Userinfo;
import com.gdyn.util.MD5;
  
@Service("UserService")  
public class UserService  {  
    @Autowired
    private UserinfoDAO userDao;  
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
    	record.setPassword(MD5.getMd5(record.getPassword()));
    	int a=userDao.updateByPrimaryKey(record);
    }
}  

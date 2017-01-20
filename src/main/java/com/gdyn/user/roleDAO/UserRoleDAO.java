package com.gdyn.user.roleDAO;

import java.util.List;

import com.gdyn.user.userRole.UserRole;

public interface UserRoleDAO {
    int deleteByPrimaryKey(String id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    List<UserRole> selectAllRole(); 
}
package com.gdyn.user.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdyn.user.userinfo.Userinfo;
/**
 * 
 * @author leo
 *
 */
@Repository
public interface UserinfoDAO {
    int deleteByPrimaryKey(String id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
    
    List<Userinfo> selectAllUser();
}
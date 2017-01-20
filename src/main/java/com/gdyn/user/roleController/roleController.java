package com.gdyn.user.roleController;

import java.security.Provider.Service;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdyn.user.service.UserService;
import com.gdyn.user.userRole.UserRole;
import com.gdyn.user.userinfo.Userinfo;
/**
 * 用户管理控制器
 * @author leo
 *取权限
 *增删查改用户
 */
@Controller
public class roleController {
	@Autowired
	UserService userservice;
	
	/**
	 * 取权限并查询所有用户
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("getrole")
	public String getRole(HttpSession session,Model model){
		List<Userinfo> users=userservice.queryAlluserinfo();
		List<UserRole> roles=userservice.getAllRole();
		//for循环装载用户角色
		for(int i=0;i<users.size();i++){
			users.get(i).setRole(roles.get(i).getRole());
		}
		UserRole role=userservice.getUserRoleById((String)session.getAttribute("id"));
		model.addAttribute("role", role.getRole());
		model.addAttribute("users", users);
		return "manage/userManage/userManage";
	}
	/**
	 * 管理员修改用户参数
	 */
	//跳转到新的页面
	@RequestMapping("updateUser")
	public String update(String id,Model model){
		model.addAttribute("user", userservice.getUserById(id));
		return "manage/userManage/updateUser";
	}
}

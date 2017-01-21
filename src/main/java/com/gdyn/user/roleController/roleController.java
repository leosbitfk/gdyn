package com.gdyn.user.roleController;

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
	public String update(String id,String err,Model model){
		//通过页面传入的id获取用户信息，返回修改页
		Userinfo user=userservice.getUserById(id);
		user.setRole(userservice.getUserRoleById(id).getRole());
		model.addAttribute("user", user);
		if(err!=null){
		model.addAttribute("err", "id已存在");
		}
		return "manage/userManage/updateUser";
	}
	//对数据库进行修改
	@RequestMapping("updateInfo")
	public String updateInfo(Userinfo user,Model model,HttpSession session){
		//在开始之前需要对空字符串进行转换
		try {
			System.out.println(user.getUsername());
			if(user.getChangeId()==""){
				user.setChangeId(null);
			}
			if(user.getEmail()==""){
				user.setEmail(null);
			}
			if(user.getUsername()==""){
				user.setUsername(null);
			}
			if(user.getPassword()==""){
				user.setPassword(null);
			}
			//因为role和id是必定有数值传入，所以role可以每次更新
			UserRole role=userservice.getUserRoleById(user.getId());
			role.setRole(user.getRole());
			userservice.updateRole(role);
			//如果所有参数都为空，就直接跳转回用户列表，免得sql报错
			if(user.getChangeId()==null
					&&user.getEmail()==null
					&&user.getUsername()==null
					&&user.getPassword()==null){
				return "redirect:/getrole";
			}
			userservice.updatePassword(user);//这个方法中有MD5转码，且能同时更新所有不为null的属性
			//对当前用户进行判断，修改的是本人的信息，则需要修改当前页的session。
			if(session.getAttribute("id").equals(user.getId())){
				session.setAttribute("user", user.getUsername());
				session.setAttribute("id", user.getId());
			}
			return "redirect:/getrole";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String err="id已存在";
			//将参数拼接成url处理
			return "redirect:/updateUser?id="+user.getId()+"&err="+err;
		}
	}
}

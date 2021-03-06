package com.gdyn.user.myUserinfo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdyn.user.service.UserService;
import com.gdyn.user.userinfo.Userinfo;
import com.gdyn.util.MD5;
/**
 * 用户信息管理页面
 * @author leo
 *
 */
@Controller
public class controller {
	@Autowired
	UserService service;
	/**
	 * 基础页面，通过session获取用户信息。
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("myuserinfo")
	public String myUserinfo(HttpSession session,Model model){
		Userinfo user=service.getUserById((String)session.getAttribute("id"));
//		model.addAttribute("id",user.getId());
//		model.addAttribute("username", user.getUsername());
//		model.addAttribute("email", user.getEmail());
		model.addAttribute("user", user);
		return"manage/userinfo/myuserinfo";
	}
	/**
	 * 对用户信息的更改
	 * @param id
	 * @param username
	 * @param email
	 * @return
	 * 因为允许修改id，所以在修改id后应注意session的id值要修改
	 * id是主键，如果修改为重复的id是会报错，所以要try/catch异常
	 * 因为是根据id查表的，所以设置多一个changid用来传参，在id查完表之后修改id
	 */
	@RequestMapping("updateuserinfo")
	public String updateuserinfo(HttpSession session,Userinfo User,Model model){
		try {
			User.setId((String)session.getAttribute("id"));
			//为了让sql语句能判断参数是否有传值，预先把空字符串赋值为null
			if(User.getEmail()==""){
				User.setEmail(null);
			}if(User.getUsername()==""){
				User.setUsername(null);
			}if(User.getChangeId()!=""){
				session.setAttribute("id", User.getChangeId());
			}else{
				User.setChangeId(null);
			}
			service.updateUserinfo(User);
			session.setAttribute("user", User.getUsername());
			return "redirect:/myuserinfo";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String error="id已存在";
			model.addAttribute("error", error);
			return "manage/userinfo/myuserinfo";
		}
	}
	/**
	 * 跳转到更改密码的网页
	 * @return
	 */
	@RequestMapping("/updatePassword")
	public String updatePassword(){
		return "manage/userinfo/updatePassword";
	}
	@RequestMapping("/password")
	public String password(String Opassword,String Npassword,
			Model model,HttpSession session){
		Userinfo user=service.getUserById((String)session.getAttribute("id"));
		if(user.getPassword().equals(MD5.getMd5(Opassword))){
			user.setPassword(Npassword);
			System.out.println(user.getId());
			service.updatePassword(user);
			model.addAttribute("error", "更改成功");
			return "manage/userinfo/updatePassword";
		}else{
			model.addAttribute("error", "原密码错误");
			return "manage/userinfo/updatePassword";
		}
		
	}
}

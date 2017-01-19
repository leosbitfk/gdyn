package com.gdyn.user.logincontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdyn.user.service.UserService;
import com.gdyn.user.userinfo.Userinfo;
import com.gdyn.util.MD5;
/**
 *登陆功能
 * @author leo
 *1、密码通过MD5加密后再进行比对
 *2、初始化session，并注入用户名，访问后台所有的页面都要对session进行验证，无session就无权限访问
 *3、因为表单是以post提交的，在后退回到页面的时候会出现缓存中的表单重新提交的问题，加上转发机制即可取消重新提交
 */
@Controller
@RequestMapping("/")
public class login {
	@Autowired
	private UserService userservice;
	public static String ID=null;
	/**
	 * 登陆验证
	 * @param id
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("/dologin")
	public String dologin(String id,String password,Model model,HttpServletRequest request){
		System.out.println(id+password);
		ID=id;//设置一个静态参数，以便后面跳转的页面能接受到参数
		String md5Password=MD5.getMd5(password);//密码进行MD5转换然后才进行比对
		try{
			Userinfo user=userservice.getUserById(id);
			if(user.getPassword().equals(md5Password)){
				HttpSession session = request.getSession();//初始化session，后台页面皆要对session进行判断
				session.setAttribute("user", user.getUsername());
				session.setAttribute("id", user.getId());
				System.out.println("yes");
				return "redirect:/login";
			}else{
				model.addAttribute("error", "密码错误");
			}
		}catch(Exception e){
			model.addAttribute("error", "无此用户");
		}
		return "account";
		
	}
	/**
	 *转发到登陆页面
	 */
	@RequestMapping("account")
	public String account(){
		return "account";
	}
	/**
	 * 转发到首页
	 */
	@RequestMapping("login")
	public String successful(Model model){
		Userinfo user=userservice.getUserById(ID);
		model.addAttribute("username", user.getUsername());//将用户名输出到前台
		return "manage/successful";
	}
}

package com.gdyn.user.myUserinfo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdyn.user.service.UserService;
import com.gdyn.user.userinfo.Userinfo;
/**
 * �û���Ϣ����ҳ��
 * @author leo
 *
 */
@Controller
public class controller {
	@Autowired
	UserService service;
	/**
	 * ����ҳ�棬ͨ��session��ȡ�û���Ϣ��
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
		return"manage/myuserinfo";
	}
	/**
	 * ���û���Ϣ�ĸ���
	 * @param id
	 * @param username
	 * @param email
	 * @return
	 */
	@RequestMapping("updateuserinfo")
	public String updateuserinfo(String id,String username,String email){
		
		return "redirect:/myuserinfo";
		
	}
}

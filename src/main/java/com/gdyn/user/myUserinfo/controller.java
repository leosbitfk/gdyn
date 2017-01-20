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
		return"manage/userinfo/myuserinfo";
	}
	/**
	 * ���û���Ϣ�ĸ���
	 * @param id
	 * @param username
	 * @param email
	 * @return
	 * ��Ϊ�����޸�id���������޸�id��Ӧע��session��idֵҪ�޸�
	 * id������������޸�Ϊ�ظ���id�ǻᱨ������Ҫtry/catch�쳣
	 * ��Ϊ�Ǹ���id���ģ��������ö�һ��changid�������Σ���id�����֮���޸�id
	 */
	@RequestMapping("updateuserinfo")
	public String updateuserinfo(HttpSession session,Userinfo User,Model model){
		try {
			User.setId((String)session.getAttribute("id"));
			//Ϊ����sql������жϲ����Ƿ��д�ֵ��Ԥ�Ȱѿ��ַ�����ֵΪnull
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
			String error="id�Ѵ���";
			model.addAttribute("error", error);
			return "manage/userinfo/myuserinfo";
		}
	}
	/**
	 * ��ת�������������ҳ
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
			model.addAttribute("error", "���ĳɹ�");
			return "manage/userinfo/updatePassword";
		}else{
			model.addAttribute("error", "ԭ�������");
			return "manage/userinfo/updatePassword";
		}
		
	}
}

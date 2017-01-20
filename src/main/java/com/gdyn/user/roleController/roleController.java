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
 * �û����������
 * @author leo
 *ȡȨ��
 *��ɾ����û�
 */
@Controller
public class roleController {
	@Autowired
	UserService userservice;
	
	/**
	 * ȡȨ�޲���ѯ�����û�
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("getrole")
	public String getRole(HttpSession session,Model model){
		List<Userinfo> users=userservice.queryAlluserinfo();
		List<UserRole> roles=userservice.getAllRole();
		//forѭ��װ���û���ɫ
		for(int i=0;i<users.size();i++){
			users.get(i).setRole(roles.get(i).getRole());
		}
		UserRole role=userservice.getUserRoleById((String)session.getAttribute("id"));
		model.addAttribute("role", role.getRole());
		model.addAttribute("users", users);
		return "manage/userManage/userManage";
	}
	/**
	 * ����Ա�޸��û�����
	 */
	//��ת���µ�ҳ��
	@RequestMapping("updateUser")
	public String update(String id,Model model){
		Userinfo user=userservice.getUserById(id);
		user.setRole(userservice.getUserRoleById(id).getRole());
		model.addAttribute("user", user);
		return "manage/userManage/updateUser";
	}
	//�����ݿ�����޸�
	@RequestMapping("updateInfo")
	public String updateInfo(Userinfo user,Model model){
		//�ڿ�ʼ֮ǰ��Ҫ�Կ��ַ�������ת��
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
			userservice.updatePassword(user);//�����������MD5ת�룬����ͬʱ�������в�Ϊnull������
			UserRole role=userservice.getUserRoleById(user.getId());
			role.setRole(user.getRole());
			userservice.updateRole(role);
			return "redirect:/getrole";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("err", "id�Ѵ���");
			return "manage/userManage/updateUser";
		}
	}
}

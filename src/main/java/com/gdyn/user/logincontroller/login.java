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
 *��½����
 * @author leo
 *1������ͨ��MD5���ܺ��ٽ��бȶ�
 *2����ʼ��session����ע���û��������ʺ�̨���е�ҳ�涼Ҫ��session������֤����session����Ȩ�޷���
 *3����Ϊ������post�ύ�ģ��ں��˻ص�ҳ���ʱ�����ֻ����еı������ύ�����⣬����ת�����Ƽ���ȡ�������ύ
 */
@Controller
@RequestMapping("/")
public class login {
	@Autowired
	private UserService userservice;
	public static String ID=null;
	/**
	 * ��½��֤
	 * @param id
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("/dologin")
	public String dologin(String id,String password,Model model,HttpServletRequest request){
		System.out.println(id+password);
		ID=id;//����һ����̬�������Ա������ת��ҳ���ܽ��ܵ�����
		String md5Password=MD5.getMd5(password);//�������MD5ת��Ȼ��Ž��бȶ�
		try{
			Userinfo user=userservice.getUserById(id);
			if(user.getPassword().equals(md5Password)){
				HttpSession session = request.getSession();//��ʼ��session����̨ҳ���Ҫ��session�����ж�
				session.setAttribute("user", user.getUsername());
				session.setAttribute("id", user.getId());
				System.out.println("yes");
				return "redirect:/login";
			}else{
				model.addAttribute("error", "�������");
			}
		}catch(Exception e){
			model.addAttribute("error", "�޴��û�");
		}
		return "account";
		
	}
	/**
	 *ת������½ҳ��
	 */
	@RequestMapping("account")
	public String account(){
		return "account";
	}
	/**
	 * ת������ҳ
	 */
	@RequestMapping("login")
	public String successful(Model model){
		Userinfo user=userservice.getUserById(ID);
		model.addAttribute("username", user.getUsername());//���û��������ǰ̨
		return "manage/successful";
	}
}

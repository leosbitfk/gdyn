package com.gdyn.user.loginout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * �ǳ�����
 * @author leo
 *�Ƴ�session�Ĳ���������session������Ч������ֹ�ǳ���������������滹�ܷ���ԭҳ�档
 */
@Controller
public class loginout {
	@RequestMapping("loginout")
	public String dologinout(HttpSession session,Model model){
		model.addAttribute("error","�ɹ��˳�");
		session.removeAttribute("user");
		session.invalidate();
		return "/account";
	}
}

package com.gdyn.user.loginout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 登出功能
 * @author leo
 *移除session的参数，并对session经行无效化，防止登出后利用浏览器缓存还能访问原页面。
 */
@Controller
public class loginout {
	@RequestMapping("loginout")
	public String dologinout(HttpSession session,Model model){
		model.addAttribute("error","成功退出");
		session.removeAttribute("user");
		session.invalidate();
		return "/account";
	}
}

package com.itwinner.webmemo.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwinner.webmemo.login.dao.LoginDao;
import com.itwinner.webmemo.login.service.impl.LoginServiceImpl;
import com.itwinner.webmemo.login.vo.LoginVo;
import com.itwinner.webmemo.user.controller.UserController;

@Controller
public class LoginController {
	@Autowired
	LoginDao dao;
	
	private static final Logger logger  = LoggerFactory.getLogger(UserController.class);
	@Autowired(required=false)
	LoginServiceImpl loginserviceimpl;
	
	// 로그인 GET
	@RequestMapping(value = "/Login/login", method = RequestMethod.GET) 
	public String loginGet(@ModelAttribute("login") LoginVo vo, HttpServletRequest request) throws Exception {
		logger.info("enter login");
		return "/Login/login"; 
		} 
	
	// 로그인 POST
	@RequestMapping(value="/Login/login", method = RequestMethod.POST)
	public String loginPost(LoginVo vo, HttpSession session,RedirectAttributes rttr) throws Exception {
		logger.info("login post");
		logger.info(vo.getUserId() + vo.getUserPw());
		LoginVo lv = loginserviceimpl.login(vo);
		logger.info(""+lv);
		if(lv != null) {
			//5.세션값생성
			session.setAttribute("id", lv.getUserId());
			rttr.addFlashAttribute("lvo", lv);
			return "redirect:/Common/main";
			}
		else {
			return "redirect:/Login/login_fail";
		}
	}
	
	// 로그인 실패
	@RequestMapping(value = "/Login/login_fail", method = RequestMethod.GET) 
		public String loginFail(LoginVo vo) throws Exception {
			logger.info("login fail");
			return "/Login/login_fail";
		
	}
	
	//로그아웃
	@RequestMapping(value = "/Login/logout", method = RequestMethod.GET)
	  public String logout(HttpSession session) throws Exception {
		session.invalidate();
		logger.info("logout");
		return "/Login/logout";
	  }
}


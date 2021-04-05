package com.itwinner.webmemo.user.controller;




import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwinner.webmemo.user.dao.UserDao;
import com.itwinner.webmemo.user.service.impl.UserServiceImpl;
import com.itwinner.webmemo.user.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired(required=false)
	UserServiceImpl userServiceImpl;
	
	// 아이디 중복 검사	
	@RequestMapping(value = "/check_id.do", method = RequestMethod.POST)
	public void check_id(@RequestParam("id") String userId, HttpServletResponse response) throws Exception{
		userServiceImpl.idChk(userId, response);
		}
		
	// 이메일 중복 검사	
	@RequestMapping(value = "/check_email.do", method = RequestMethod.POST)
	public void check_email(@RequestParam("email") String userEmail, HttpServletResponse response) throws Exception{
		userServiceImpl.emailChk(userEmail, response);
		}
	
	
	// 이메일 인증	
	@ResponseBody
	@RequestMapping("/check_email_auth.do")
	public Map<String, Object> check_email_auth(String userEmail) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		String key = userServiceImpl.createKey();
		userServiceImpl.sendEmail(userEmail, key);
		map.put("key", key);
		return map;
		}
	
	
	// 회원가입 GET	
	@RequestMapping(value="/User/regist",method = RequestMethod.GET)
	public void registGet() throws Exception {
		logger.info("get register");
	}
	
	// 회원가입 POST		
	@RequestMapping(value = "/User/regist", method = RequestMethod.POST)
	public String registPost(@ModelAttribute UserVo vo, RedirectAttributes rttr, HttpServletResponse response) throws Exception{
		rttr.addFlashAttribute("result", userServiceImpl.insertUser(vo, response));
		logger.info("register complete");
		return "redirect:/User/regist_complete";
		}
	
	//회원가입 완료
	@RequestMapping(value="/User/regist_complete")
	public String registComplete() throws Exception{
		return "/User/regist_complete";
	}
	
	// 유저인증 GET
	@RequestMapping(value = "/User/authenticate", method = RequestMethod.GET)
	public String authenticateUserGet(HttpSession session, Model model) throws Exception {
		logger.info("enter userauthentic");
		String userId = (String) session.getAttribute("id");
		UserVo vo = userServiceImpl.getUserInfo(userId);
		model.addAttribute("UserVo",vo);
		return "User/authenticate";
		}
	
	// 유저인증 POST
	@RequestMapping(value="/User/authenticate", method = RequestMethod.POST)
	public String authenticateUserPost(UserVo vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.info("userauthentic post");
		String userId = (String) session.getAttribute("id");
		logger.info(userId + vo.getUserPw());
		UserVo uv = userServiceImpl.selectUserPw(userId);
		logger.info(uv.getUserPw() + "/" + vo.getUserPw());
		if(vo != null && uv.getUserPw().equals(vo.getUserPw())) {
			return "redirect:/User/authenticate_complete";
		}
		else {
			return "redirect:/User/authenticate";
		}
	}
	
	// 유저 인증 성공후 페이지
	@RequestMapping(value = "/User/authenticate_complete", method = RequestMethod.GET)
	public String authenticateComplete() {
		logger.info("authenticate complete");
		return "/User/authenticate_complete";
	}
	
	// 패스워드 변경 GET
	@RequestMapping(value = "/User/change_pw", method = RequestMethod.GET)
	public String changePwGet() {
		logger.info("enter changepw");
		return "/User/change_pw";
	}
	
	// 패스워드 변경 POST
	@RequestMapping(value = "/User/change_pw", method = RequestMethod.POST)
	public String changePwPost(UserVo vo, HttpSession session) throws Exception {
		logger.info("changepw post");
		String userId = (String) session.getAttribute("id");
		UserVo uv = userDao.getUserInfo(userId);
		if(userId != null) {
			uv.setUserPw(vo.getUserPw());
			userServiceImpl.updateUserPw(uv);
		}
		return "redirect:/User/change_pw_complete";

	}
	// 패스워드 변경 완료
	@RequestMapping(value = "/User/change_pw_complete", method = RequestMethod.GET)
	public void changePwComplete() {
		logger.info("change pw success");
	}
	
	// 개인정보 변경 GET
	@RequestMapping(value = "/User/change_info", method = RequestMethod.GET)
	public void changeInfoGet(UserVo vo, HttpSession session) {
		logger.info("enter changeinfo");
		String userId = (String) session.getAttribute("id");
		UserVo uv = userDao.getUserInfo(userId);
		session.setAttribute("name", uv.getUserName());
		session.setAttribute("birthdate",uv.getUserBirthDate());
		session.setAttribute("email", uv.getUserEmail());
		session.setAttribute("gender", uv.getUserGender());
	}
	
	// 개인정보 변경 POST	
	@RequestMapping(value = "/User/change_info", method = RequestMethod.POST)
	public String changeInfoPost(@ModelAttribute UserVo vo, RedirectAttributes rttr, HttpSession session, HttpServletResponse response) throws Exception{
		String userId = (String) session.getAttribute("id");
		UserVo uv = userDao.getUserInfo(userId);
		//변경하는 조건들을 분류하여 원하는 부분만 변경 가능
		if(vo.getUserAddress().equals(",,") && vo.getUserEmail() == "" && vo.getUserPhone() == "") {
			return "redirect:/User/change_info_fail";
		}
		else if(vo.getUserAddress().equals(",,")  && vo.getUserEmail() == "") {
			uv.setUserPhone(vo.getUserPhone());
		}
		else if(vo.getUserEmail() == "" && vo.getUserPhone() == "") {
			uv.setUserAddress(vo.getUserAddress());
		}
		else if(vo.getUserAddress().equals(",,") && vo.getUserPhone() == "") {
			uv.setUserEmail(vo.getUserEmail());
		}
		else if(vo.getUserAddress().equals(",,")) {
			uv.setUserEmail(vo.getUserEmail());
			uv.setUserPhone(vo.getUserPhone());
		}
		else if(vo.getUserPhone() == "") {
			uv.setUserAddress(vo.getUserAddress());
			uv.setUserEmail(vo.getUserEmail());
		}
		else if(vo.getUserEmail() == "") {
			uv.setUserAddress(vo.getUserAddress());
			uv.setUserPhone(vo.getUserPhone());
		}
		else {
			uv.setUserAddress(vo.getUserAddress());
			uv.setUserEmail(vo.getUserEmail());
			uv.setUserPhone(vo.getUserPhone());
		}
		userServiceImpl.updateUserInfo(uv, response);
		return "redirect:/User/change_info_complete";
		
	}
	
	// 개인정보 변경 실패
	@RequestMapping(value="/User/change_info_fail", method = RequestMethod.GET)
	public void changeInfoFail() {
		logger.info("userinfo change fail");
	}
	
	// 개인정보 변경 완료
	@RequestMapping(value="/User/change_info_complete", method = RequestMethod.GET)
	public void changeInfoComplete() {
		logger.info("userinfo change complete");
	}
	
	// 패스워드 찾기 GET	
	@RequestMapping(value = "/User/find_pw", method = RequestMethod.GET)
	public String findPwGet() {
		logger.info("enter find pw");
		return "/User/find_pw";
	}
	
	// 패스워드 찾기 POST	
	@RequestMapping(value="/User/find_pw",method = RequestMethod.POST)
	public void findPwPost(@ModelAttribute UserVo vo, HttpServletResponse response) throws Exception{
		userServiceImpl.findPw(response,vo);
	}
	
	// 아이디 찾기 GET	
	@RequestMapping(value = "/User/find_id", method = RequestMethod.GET)
	public String findIdGet() {
		logger.info("enter find id");
		return "/User/find_id";
	}
	
	// 아이디 찾기 POST	
	@RequestMapping(value = "/User/find_id", method = RequestMethod.POST)
	public ModelAndView findIdPost(UserVo vo,ModelAndView mv) throws Exception{
		Object result =  userServiceImpl.findId(vo);
		if(result == null) {
			logger.info("findID fail");
			mv.setViewName("/User/find_id_fail");
			
		}
		else {
		logger.info("find id complete");
		mv.setViewName("/User/find_id_complete");
		mv.addObject("ID", result);
		}
		return mv;
	}
	
	// 아이디 찾기 완료
	@RequestMapping(value = "/User/find_id_complete", method = RequestMethod.GET) 
	public String findIdComplete() throws Exception {
		logger.info("find id complete");
		return "/User/find_id_complete";
		}
	
	// 아이디 찾기 실패
	@RequestMapping(value = "/User/find_id_fail", method = RequestMethod.GET) 
	public String findIdFail() throws Exception {
		logger.info("findidfail enter");
		return "/User/find_id_fail";
	}
	
	// 회원 탈퇴 POST	
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String deleteUser(UserVo vo, HttpSession session) throws Exception{
		String userId = (String)session.getAttribute("id");
		userServiceImpl.deleteUser(userId);
		return "redirect:/User/delete_complete";
	}
	
	// 회원 탈퇴 완료
	@RequestMapping(value="/User/delete_complete", method = RequestMethod.GET)
	public String deleteUserGet() throws Exception{
		return "/User/delete_complete";
	}
}

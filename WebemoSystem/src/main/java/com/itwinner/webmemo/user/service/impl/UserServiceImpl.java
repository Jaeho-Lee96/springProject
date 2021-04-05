package com.itwinner.webmemo.user.service.impl;

import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.sql.Date;
//import java.text.SimpleDateFormat;
//
//import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwinner.webmemo.user.controller.UserController;
import com.itwinner.webmemo.user.dao.UserDao;
import com.itwinner.webmemo.user.service.UserService;
import com.itwinner.webmemo.user.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@Override
	public Object selectUser() throws Exception {
		return dao.selectUser();
	}
	@Override
	public int insertUser(UserVo vo, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (dao.idChk(vo.getUserId()) == 1) {
			out.println("<script>");
			out.println("alert('동일한 아이디가 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else if (dao.emailChk(vo.getUserEmail()) == 1) {
			out.println("<script>");
			out.println("alert('동일한 이메일이 있습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return 0;
		} else {
			//회원가입
			dao.insertUser(vo);
			//인증 메일 전송
			return 1;
		}
	}

	
	@Override
	public int updateUserInfo(UserVo vo, HttpServletResponse response) throws Exception {
		dao.updateUserInfo(vo);
		return 1;
	}

	@Override
	public void sendEmail(String userEmail, String authNum) throws Exception {
		// Mail Server 설정
				String charSet = "utf-8";
				String hostSMTP = "smtp.naver.com";
				String hostSMTPid = "cys950331@naver.com";
				String hostSMTPpwd = "!s04248588";

				// 보내는 사람 EMail, 제목, 내용
				String fromEmail = "cys950331@naver.com";
				String fromName = "웹메모 시스템";
				String subject = "";
				String msg = "";

				// 회원가입 메일 내용
				subject = "웹메모 시스템 회원가입 인증 메일입니다.";
				msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
				msg += "<h3 style='color: blue;'>";
				msg += "<div style='font-size: 130%'>";
				msg += "하단의 인증 번호 입력 시 정상적으로 회원가입이 완료됩니다.</div><br/>";
				msg += "인증번호 [" +authNum + "]";

				// 받는 사람 E-Mail 주소
				String mail = userEmail;
				try {
					HtmlEmail email = new HtmlEmail();
					email.setDebug(true);
					email.setCharset(charSet);
					email.setSSL(true);
					email.setHostName(hostSMTP);
					email.setSmtpPort(587);

					email.setAuthentication(hostSMTPid, hostSMTPpwd);
					email.setTLS(true);
					email.addTo(mail, charSet);
					email.setFrom(fromEmail, fromName, charSet);
					email.setSubject(subject);
					email.setHtmlMsg(msg);
					email.send();
					} 
				catch (Exception e) {
					System.out.println("메일발송 실패 : " + e);
					}		
			}
		
	
	@Override
	public void updateUserPw(UserVo vo) throws Exception {
		dao.updateUserPw(vo);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		dao.deleteUser(userId);
		}
	
	
	@Override
	public Object findId(UserVo vo) {
		return dao.findId(vo);
	}
	
	@Override
	public void sendEmail(UserVo vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "cys950331@naver.com";
		String hostSMTPpwd = "!s04248588";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "cys950331@naver.com";
		String fromName = "웹메모 시스템";
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "웹메모시스템 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getUserId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getUserPw() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = vo.getUserEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); 

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
		
	
	@Override
	public void findPw(HttpServletResponse response, UserVo vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		logger.info(vo.getUserId());

		UserVo ck = dao.getUserInfo(vo.getUserId());
		PrintWriter out = response.getWriter();
		// 가입된 아이디가 없으면
		if(dao.checkId(vo.getUserId()) == null) {
			out.print("등록되지 않은 아이디입니다.");
			out.close();
		}
		// 가입된 이메일이 아니면
		else if(!vo.getUserEmail().equals(ck.getUserEmail())) {
			out.print("등록되지 않은 이메일입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			vo.setUserPw(pw);
			// 비밀번호 변경
			dao.updateUserPw(vo);
			// 비밀번호 변경 메일 발송
			sendEmail(vo, "findpw");

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}
		
	}
	@Override
	public UserVo getUserInfo(String userId) throws Exception {
		UserVo vo = null;
		vo = dao.getUserInfo(userId);
		return vo;
	}
	
	@Override
	public UserVo checkId(String userId) throws Exception {
		return dao.checkId(userId);
	}
	@Override
	public void idChk(String userId, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.idChk(userId));
		out.close();
	}
	@Override
	public UserVo selectUserPw(String userId) throws Exception {
		return dao.selectUserPw(userId);
	}
	@Override
	public void emailChk(String userEmail, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.emailChk(userEmail));
		out.close();
		}
	@Override
	public String createKey() throws Exception {
		String key = "";
		Random rd = new Random();
		 for(int i=0; i<9; i++) {
			 key += rd.nextInt(10);
		 }
		 return key;
	}
	
}

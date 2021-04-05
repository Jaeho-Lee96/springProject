package com.itwinner.webmemo.login.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwinner.webmemo.login.dao.LoginDao;
import com.itwinner.webmemo.login.service.LoginService;
import com.itwinner.webmemo.login.vo.LoginVo;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginDao dao;

	@Override
	public LoginVo login(LoginVo vo) throws Exception {
		LoginVo lv = new LoginVo();
		// 아이디와 패스워드를 이용한 조회로 로그인 구현
		try {
			lv = dao.login(vo);
		} catch (Exception e) {
			e.printStackTrace();
			lv = null; // 해당 정보로 로그인 조회값 없으면 null 리턴
		}
		return lv; 
	}	

}

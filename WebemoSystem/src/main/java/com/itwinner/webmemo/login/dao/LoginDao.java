package com.itwinner.webmemo.login.dao;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwinner.webmemo.login.vo.LoginVo;

@Repository
public class LoginDao {

	@Autowired
	private SqlSessionTemplate session;
	
	
	private String PATH =  "loginMapper.";
	
	public LoginVo login(LoginVo vo) throws Exception{
		return session.selectOne(PATH + "login",vo);
	}

}

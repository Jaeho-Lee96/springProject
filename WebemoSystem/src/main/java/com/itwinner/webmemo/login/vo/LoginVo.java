package com.itwinner.webmemo.login.vo;

import lombok.Data;

@Data
public class LoginVo {
	private String userId;  /* 회원 아이디 */
	private String userPw;	/* 회원 패스워드 */
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
}

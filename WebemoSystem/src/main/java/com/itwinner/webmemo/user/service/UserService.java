package com.itwinner.webmemo.user.service;


import javax.servlet.http.HttpServletResponse;

import com.itwinner.webmemo.user.vo.UserVo;

public interface UserService {
	public Object selectUser() throws Exception; // 회원 목록 전체 불러오기
	public UserVo getUserInfo(String userId) throws Exception; // 개인정보수정에 이용하는 유저정보 불러오기
	public UserVo selectUserPw(String userId) throws Exception;
	public int insertUser(UserVo vo, HttpServletResponse response) throws Exception; // 화원가입
	public int updateUserInfo(UserVo vo, HttpServletResponse response) throws Exception; // 유저정보 갱신
	public void updateUserPw(UserVo vo) throws Exception; // 비밀번호 갱신
	public void deleteUser(String userId) throws Exception; // 회원 탈퇴
	public Object findId(UserVo vo) throws Exception; //아이디 찾기
	public void sendEmail(UserVo vo, String div) throws Exception; // 패스워드 변경시 임시 비밀번호를 받기 위한 메일 전송
	public void findPw(HttpServletResponse response, UserVo vo) throws Exception;
	public UserVo checkId(String userId) throws Exception; // 패스워드 찾기에 이용되는 아이디 조회
	public void idChk(String userId,HttpServletResponse response) throws Exception; // 회원가입시 아이디 중복을 검사
	public void emailChk(String userEmail, HttpServletResponse response) throws Exception; //회원가입시 이메일 중복 검사
	public String createKey() throws Exception; // 이메일 인증키 생성 함수
	public void sendEmail(String userEmail, String authNum) throws Exception; //회원가입시 이메일 인증을 위한 메일 전송

	
}

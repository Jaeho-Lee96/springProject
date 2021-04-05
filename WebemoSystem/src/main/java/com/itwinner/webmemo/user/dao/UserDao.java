package com.itwinner.webmemo.user.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwinner.webmemo.user.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private String PATH =  "userMapper.";
	
	/**
	 * 
	 * @return 회원목록
	 */
	public Object selectUser() {
		return session.selectList(PATH + "getUserList");
	}
	
	/**
	 * 
	 * @param userId
	 * @return 유저정보
	 */
	public UserVo getUserInfo(String userId){
		UserVo vo = session.selectOne(PATH + "getUserInfo",userId);
		return vo;
	}
	
	
	/**
	 * 
	 * @param vo
	 */
	public int insertUser(UserVo vo) {
		//
		return session.insert(PATH + "insertUser", vo);
	}
	
	/**
	 * 
	 * @param vo
	 */
	public void updateUserInfo(UserVo vo) {
		session.update(PATH + "updateUserInfo", vo);
	}
	/**
	 * 
	 * @param vo
	 */
	public void updateUserPw(UserVo vo) {
		session.update(PATH + "updateUserPw", vo);
	}
	/**
	 * 
	 * @param userId
	 */
	public void deleteUser(String userId) {
		 session.delete(PATH + "deleteUser", userId);
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public Object findId(UserVo vo) {
		return session.selectOne(PATH+"findId",vo);
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UserVo checkId(String userId) {
		UserVo vo = session.selectOne(PATH + "checkId", userId);
		return vo;
	}
	
	/**
	 * 
	 * @param vo
	 * @return
	 */
	public int idChk(String userId) {
		int result = session.selectOne(PATH + "idChk", userId);
		return result;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UserVo selectUserPw(String userId) {
		return session.selectOne(PATH + "selectUserPw", userId);
		
	}
	
	/**
	 * 
	 * @param userEmail
	 * @return
	 */
	public int emailChk(String userEmail) {
		return session.selectOne(PATH + "emailChk",userEmail);
	}
	
	
}

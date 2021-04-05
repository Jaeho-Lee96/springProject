package com.itwinner.webmemo.user.vo;

import lombok.Data;

@Data
public class UserVo {
	private String userId;			/** 회원 아이디 */
	private String userPw;			/** 회원 패스워드 */
	private String userName;		/** 회원 이름 */
	private String userEmail;		/** 회원 이메일 */
	private String userBirthDate;	/** 회원 생일 */
	private String userGender;		/** 회원 성별 */
	private String userAddress;		/** 회원 주소 */
	private String userPhone;		/** 회원 번호 */
	private String userSignupDate;	/** 회원 가입일 */
	private boolean isSuper;		/** 수퍼회원여부 */
	
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserBirthDate() {
		return userBirthDate;
	}
	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserSignupDate() {
		return userSignupDate;
	}
	public void setUserSignupDate(String userSignupDate) {
		this.userSignupDate = userSignupDate;
	}
	public boolean isSuper() {
		return isSuper;
	}
	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}
	
	
}

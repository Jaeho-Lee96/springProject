package com.itwinner.webmemo.memo.vo;

import lombok.Data;

@Data
public class MemoVo {
	private int memoNum;
	private String userId;
	private String memoName;
	private String memoContext;
	private String memoWriteDate;
	private String memoUpdateDate;
	private String memoUpdateUser;
	private String memoFormat;
	private boolean memoFav;
	private String shareUserId;

	// 추가 컬럼
	private Integer displayPost;
	private Integer postNum;
	private String	keyword;
	private String 	searchType;

	private String memoShareDate;
	private boolean shareMemoFav;
	private int shareMemoNum;
		
	private String memoForm;
	private String formName;
	

	
	public int getMemoNum() {
		return memoNum;
	}
	public void setMemoNum(int memoNum) {
		this.memoNum = memoNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMemoName() {
		return memoName;
	}
	public void setMemoName(String memoName) {
		this.memoName = memoName;
	}
	public String getMemoContext() {
		return memoContext;
	}
	public void setMemoContext(String memoContext) {
		this.memoContext = memoContext;
	}
	public String getMemoWriteDate() {
		return memoWriteDate;
	}
	public void setMemoWriteDate(String memoWriteDate) {
		this.memoWriteDate = memoWriteDate;
	}
	public String getMemoUpdateDate() {
		return memoUpdateDate;
	}
	public void setMemoUpdateDate(String memoUpdateDate) {
		this.memoUpdateDate = memoUpdateDate;
	}
	public String getMemoUpdateUser() {
		return memoUpdateUser;
	}
	public void setMemoUpdateUser(String memoUpdateUser) {
		this.memoUpdateUser = memoUpdateUser;
	}
	public String getMemoFormat() {
		return memoFormat;
	}
	public void setMemoFormat(String memoFormat) {
		this.memoFormat = memoFormat;
	}
	public boolean isMemoFav() {
		return memoFav;
	}
	public void setMemoFav(boolean memoFav) {
		this.memoFav = memoFav;
	}
	public String getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}
	public Integer getDisplayPost() {
		return displayPost;
	}
	public void setDisplayPost(Integer displayPost) {
		this.displayPost = displayPost;
	}
	public Integer getPostNum() {
		return postNum;
	}
	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getMemoShareDate() {
		return memoShareDate;
	}
	public void setMemoShareDate(String memoShareDate) {
		this.memoShareDate = memoShareDate;
	}
	public boolean isShareMemoFav() {
		return shareMemoFav;
	}
	public void setShareMemoFav(boolean shareMemoFav) {
		this.shareMemoFav = shareMemoFav;
	}
	public int getShareMemoNum() {
		return shareMemoNum;
	}
	public void setShareMemoNum(int shareMemoNum) {
		this.shareMemoNum = shareMemoNum;
	}
	public String getMemoForm() {
		return memoForm;
	}
	public void setMemoForm(String memoForm) {
		this.memoForm = memoForm;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
}

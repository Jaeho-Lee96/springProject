package com.itwinner.webmemo.memo.page;

public class Page {
	
	//현재 페이지 번호
	private int num;
	
	//게시물 수
	private int count;
	
	//한 페이지 출력 수
	private int postNum = 10;
	
	//하단 페이징 번호
	private int pageNum;
	
	//출력할 게시물
	private int displayPost;
	
	//페이징 번호의 수
	private int pageNumCnt = 10;
	
	//마지막 번호
	private int endPageNum;
	
	//첫 번호
	private int startPageNum;
	
	//다음 이전 표시여부
	private boolean prev;
	private boolean next;
	
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		dataCalc();
	}
	public int getPostNum() {
		return postNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getDisplayPost() {
		return displayPost;
	}
	public int getPageNumCnt() {
		return pageNumCnt;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	
	private void dataCalc() {
		 
		 // 마지막 번호
		 this.endPageNum = (int)(Math.ceil((double)num / (double)pageNumCnt) * pageNumCnt);
		 
		 // 시작 번호
		 startPageNum = this.endPageNum - (pageNumCnt - 1);
		 
		 // 마지막 번호 재계산
		 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNumCnt));
		 
		 if(this.endPageNum > endPageNum_tmp) {
		  this.endPageNum = endPageNum_tmp;
		 }
		 prev = startPageNum == 1 ? false : true;
		 next = endPageNum * pageNumCnt >= count ? false : true;
		 
		 displayPost = (num - 1) * postNum;
		 
		}

	public String getSearchTypeKeyword() {
		 
		 if(searchType.equals("") || keyword.equals("")) {
		  return ""; 
		 } else {
		  return "&searchType=" + searchType + "&keyword=" + keyword; 
		 }
	}
}

package com.itwinner.webmemo.memo.service;

import java.util.HashMap;
import java.util.List;

import com.itwinner.webmemo.memo.vo.MemoVo;

public interface MemoService {
	
	/**memoInsert
	 * 메모 작성
	 * MemoVo를 받아 Insert 
	 * @return
	 */
	int memoInsert(MemoVo vo);

	/**memoWrite
	 * @param vo
	 * 메모 제목 메모 내용만 기입하면 되는 작성 글
	 * @return
	 */
	int memoWrite(MemoVo vo);
	
	/**memoSelect 
	 * @return 메모 목록 List 형태로 반환
	 */
	List<MemoVo> memoSelect();
	
	int memoDelete(int memoNum);
	
	List<MemoVo> userMemoSelect(String userId);
		
	List<MemoVo> getMemoByMemoNum(int memoNum);
	
	MemoVo getMemoVoByMemoNum(int memoNum);
	
	int updateMemoByMemoVo(MemoVo vo);
	
	//유저가 쓴 게시물 개수
	public int count(String userId) throws Exception;
	
	//페이징
	public List<MemoVo> listPage(String user, int displayPost, int postNum) throws Exception;
	
	//검색
	public List<MemoVo> listPageSearch(String user, int displayPost, int postNum, String searchType, String keyword) throws Exception;
	
	//검색 count
	public int listPageCount(String serachType, String keyword, String user) throws Exception;
	
	//유저 리스트 가져오기
	public List<String> listUser(String userId) throws Exception;
	
	//메모 번호 split
	public List<Integer> splitString(String memoNums) throws Exception;
	
	//메모 공유 기능
	public int memoShare(List<String> userId, List<Integer> memoNum) throws Exception;
	
	//공유 된 메모 및 내 메모 검색
	public List<HashMap<String, Object>> unionPageSearch(String user, int displayPost, int postNum, String searchType, String keyword) throws Exception;
	
	//공유 된 메모 및 내 메모 count
	public Integer unionPageCount(String serachType, String keyword, String user) throws Exception;
	
	//공유 된 메모 ID로 공유된 유저 SELECT
	public List<String> selectSharedUser(Integer memoNum) throws Exception;
	
	//공유 취소
	public Integer cancleShare(Integer memoNum, String userId) throws Exception;
	
	//공유 된 메모 즐겨 찾기 수정
	public Integer editFavSharedMemo(Integer memoNum, String userId, Integer fav) throws Exception;
	
	//내 메모 즐겨 찾기 수정
	public Integer editFavMemo(Integer memoNum, Integer fav) throws Exception;
	
	//공유 돼지 않은 메모 삭제
	public Integer deleteNotSharedMemo(Integer memoNum) throws Exception;
	
	//메모 폼 추가
	public Integer addForm(String userId, String formName ,String formData) throws Exception;
	
	//메모 폼 SELECT ONE
	public HashMap<String, Object> getOneForm(Integer formId) throws Exception;
	
	//메모 폼 SELECT LIST
	public List<HashMap<String, Object>> getUserForm(String UserId) throws Exception;
	
	//메모 폼 DELTE
	public Integer deleteForm(Integer formId) throws Exception;
	
	//즐찾 메모 select
	public List<HashMap<String, Object>> getFavMemo(String userId) throws Exception;
	
	//슈퍼 유저 찾기
	public String getSuper() throws Exception;
	
	//슈퍼 유저 검색 Count
	public Integer getSuperCount(String searchType, String keyword) throws Exception;
	
	//슈퍼 유저 감색 기능
	public List<HashMap<String, Object>> getSuperSearchedMemo(Integer displayPost, Integer postNum, String searchType, String keyword) throws Exception;
}

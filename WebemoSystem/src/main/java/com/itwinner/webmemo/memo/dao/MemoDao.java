package com.itwinner.webmemo.memo.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwinner.webmemo.memo.vo.MemoVo;

@Repository
public class MemoDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public List<MemoVo> selectMemo() {
		return session.selectList("memoMapper.getMemoList");
	}
	
	/**
	 * SelectUserMemo 
	 * @param userId
	 * @return userId가 작성한 메모리스트
	 */
	
	public List<MemoVo> selectUserMemo(String userId) {
		return session.selectList("memoMapper.getUserMemo", userId);
	}
	
	/**
	 * getMemoByMemoId 
	 * @param memo_number 메모 번호
	 * @return 메모번호로 조회한 메모리스트
	 */
	public List<MemoVo> getMemoByMemoId(int memoNum) {
		return session.selectList("memoMapper.getMemoByMemoNum", memoNum);
	}
	
	/**
	 * getMemoVoByMemoId
	 * @param memoNum
	 * @return 메모 단일 조회
	 */
	public MemoVo getMemoVoByMemoId(int memoNum) {
		return session.selectOne("memoMapper.getMemoVoByMemoNum", memoNum);
	}
	
	/**
	 * insertMemo
	 * @param memoVo
	 * @return 1=성공 0=실패
	 */
	public int insertMemo(MemoVo memoVo) {
		return session.insert("memoMapper.insertMemo", memoVo);
	}
	
	/**
	 * writeMemo
	 * @param memoVo
	 * @return 1=성공 0=실패
	 * @throws Exception
	 */
	public int writeMemo(MemoVo memoVo) throws Exception {
		return session.insert("memoMapper.writeMemo", memoVo);
	}
	
	/**
	 * deleteMemo
	 * memo_share table에서 해당 메모가 포함된 공유 튜플을 삭제 후 메모 제거
	 * @param 메모 number
	 * @return 1=성공 0=실패
	 */
	public int deleteMemo(int id) {
		session.delete("memoMapper.superDeleteShare", id);
		return session.delete("memoMapper.deleteMemo", id);
	}
	
	/**
	 * deleteNorSharedMemo
	 * 일반 메모 삭제 기능 해당 메모가 공유되어 있으면 삭제기능 동작 X
	 * @param memoNum
	 * @return 1=성공 0=실패
	 */
	public Integer deleteNotSharedMemo(Integer memoNum) {
		return session.delete("memoMapper.deleteNotShared", memoNum);
	}
	
	/**
	 * updateMemo
	 * 메모VO를 입력으로 받아 해당 메모 업데이트 
	 * @param memoVo
	 * @return 1=성공 0=실패
	 */
	public int updateMemo(MemoVo memoVo) {
		return session.update("memoMapper.updateMemoByMemoId", memoVo);
	}
	
	/**
	 * searchMemo
	 * @param user 유저 ID
	 * @param displayPost 조회할 메모 개수
	 * @param postNum 0 -> 10 -> 20 페이지 목록
	 * @param searchType 검색 타입 title(제목), userId(유저아이디), context(내용)
	 * @param keyword '%키워드%'
	 * @return 검색된 메모 List<MemoVo>
	 */
	public List<MemoVo> searchMemo(String user, Integer displayPost, Integer postNum, String searchType, String keyword) {
		// make param to "%content" foramt to use like query.
		HashMap<String, Object> param = new HashMap<String, Object>();
		keyword = '%' + keyword + '%';
		param.put("displayPost", displayPost);
		param.put("postNum", postNum);
		param.put("user", user);
		param.put("searchType", searchType);
		param.put("keyword", keyword);
		System.out.println(param.toString());
		return session.selectList("memoMapper.searchMemoByContent", param);
	}
	
	/**
	 * count
	 * @param userId
	 * @return 해당 유저의 작성글 개수
	 * @throws Exception
	 */
	public int count(String userId) throws Exception {
		return session.selectOne("memoMapper.countUserMemo", userId);
	}
	
	public List<MemoVo> listPage(String user, Integer displayPost, Integer postNum) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("user", user);
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		System.out.println(data.toString());

		return session.selectList("memoMapper.selectMemoForPaging", data);
	}
	
	public int searchCount(String searchType, String keyword, String user) throws Exception{
		HashMap<String, Object> data = new HashMap<String, Object>();
		keyword = '%' + keyword + '%';
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		data.put("user", user);
		
		return session.selectOne("memoMapper.searchCount", data);
	}
	
	public List<String> memoUserList(String userId) throws Exception{
		return session.selectList("memoMapper.getAllUser", userId);
	}
	
	//TODO HashMap VO로 바꿔주기
	public int memoShare(String userId, int memoNum, String shareTime) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("memoNum", memoNum);
		data.put("shareTime", shareTime);
		
		System.out.println(data.toString());
		return session.insert("memoMapper.insertShareMemo", data);
	}
	//TODO HashMap VO로 바꿔주기
	public List<HashMap<String, Object>> searchUnionMemo(String user, Integer displayPost, Integer postNum, String searchType, String keyword) {
		// make param to "%content" foramt to use like query.
		HashMap<String, Object> param = new HashMap<String, Object>();
		keyword = '%' + keyword + '%';
		param.put("displayPost", displayPost);
		param.put("postNum", postNum);
		param.put("userId", user);
		param.put("searchType", searchType);
		param.put("keyword", keyword);
		System.out.println(param.toString());
		return session.selectList("memoMapper.selectUnionMemo", param);
	}
	
	//TODO HashMap VO로 바꿔주기
	public Integer searchUnionCount(String searchType, String keyword, String user) throws Exception{
		HashMap<String, Object> data = new HashMap<String, Object>();
		keyword = '%' + keyword + '%';
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		data.put("userId", user);
		
		return session.selectOne("memoMapper.searchUnionCount", data);
		
	}
	
	//공유된 유저 가져오기 자기 자신 제외
	public List<String> selectSharedUser(Integer memoNum) throws Exception{
		return session.selectList("memoMapper.selectSharedUser", memoNum);
	}
	
	//공유 취소 하기
	//TODO HashMap Vo로 바꾸기
	public Integer cancleShare(Integer memoNum, String userId) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("memoNum", memoNum);
		data.put("userId", userId);
		return session.delete("memoMapper.deleteShare", data);
	}
	
	public Integer updateMemoFav(HashMap<String, Object> data) throws Exception {
		return session.update("memoMapper.updateMemoFav", data);
	}
	
	public Integer updateShareMemoFav(HashMap<String, Object> data) throws Exception {
		return session.update("memoMapper.updateShareMemoFav", data);
	}
	
	//메모 FORM 추가
	public Integer insertForm(HashMap<String, Object> data) throws Exception {
		return session.insert("memoMapper.insertMemoForm", data);
	}
	
	//메모 FORM SELECT ONE
	public HashMap<String, Object> selectOneForm(Integer formId) throws Exception {
		return session.selectOne("memoMapper.selectMemoForm", formId);
		
	}
	
	//메모 FORM DELETE
	public Integer deleteForm(Integer formId) throws Exception {
		return session.delete("memoMapper.deleteForm" , formId);
	}
	
	//메모 폼 SELECT LIST
	public List<HashMap<String, Object>> selectUserForm(String userId) throws Exception {
		return session.selectList("memoMapper.selectUserMemoForm", userId);
	}
	
	//즐찾 메모들 SELECT
	public List<HashMap<String, Object>> selectFavMemo(String userId) throws Exception {
		return session.selectList("memoMapper.selectFavMemo", userId);
	}
	
	//슈퍼 유저 SELECT
	public String selectSuper() throws Exception {
		return session.selectOne("memoMapper.selectSuperUser");
	}
	
	//슈퍼 유저 검색 Count
	public Integer selectSuperCount(HashMap<String, Object> data) throws Exception {
		return session.selectOne("memoMapper.superSearchCount", data);
	}
	
	//슈퍼 유저 검색
	public List<HashMap<String, Object>> selectSuperSearchedMemo(HashMap<String, Object> data) throws Exception {
		System.out.println(data.toString());
		System.out.println(session.selectList("memoMapper.superSearchMemoByContent", data));
		return session.selectList("memoMapper.superSearchMemoByContent", data);
	}
	
}

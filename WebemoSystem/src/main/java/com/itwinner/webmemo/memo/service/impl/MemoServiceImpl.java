package com.itwinner.webmemo.memo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwinner.webmemo.memo.dao.MemoDao;
import com.itwinner.webmemo.memo.service.MemoService;
import com.itwinner.webmemo.memo.vo.MemoVo;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	MemoDao dao;

	@Override
	public int memoInsert(MemoVo vo) {
			return dao.insertMemo(vo);
	}
	
	@Override
	public int memoWrite(MemoVo vo) {
		vo.setMemoFav(false);
		
		// set MemoVo.memoWriteDate to Current datetime via dateFormat
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Date current = new Date();
		String time = dateFormat.format(current);
		vo.setMemoWriteDate(time);
		
		// end of set Vo
		try {
		return dao.insertMemo(vo);
		}  catch (Exception e) {
			return 0;
		}
	}
	/**
	 * 	@param 업데이트 하고 싶은 MemoVo
	 *  @return 업데이트 성공시 1 아닐시 0
	 */
	@Override
	public int updateMemoByMemoVo(MemoVo vo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Date current = new Date();
		String time = dateFormat.format(current);
		vo.setMemoUpdateDate(time);
		//TODO session처리 완료시 UpdateUser를 세션의 User로 변경 필요
		vo.setMemoUpdateUser(vo.getMemoUpdateUser());
		return dao.updateMemo(vo);
	}
	
	/**
	 * @return MemoVo 모든 메모 반
	 */
	@Override
	public List<MemoVo> memoSelect() {
		return dao.selectMemo();
	}
	
	/**
	 * @param userId 
	 * @return MemoVo 유저의 모든 메모 반환
	 */
	@Override
	public List<MemoVo> userMemoSelect(String userId) {	
		return dao.selectUserMemo(userId);
	}

	/**
	 * @param memoNum
	 * @return MemoVo 메모 번호로 메모 조회
	 */
	@Override
	public List<MemoVo> getMemoByMemoNum(int memoNum) {
		return dao.getMemoByMemoId(memoNum);
	}

	/**
	 * 메모 번호로 selectOne 단일 값 조회
	 * @param memoNum
	 * @return MemoVo 검색 결과
	 */
	@Override
	public MemoVo getMemoVoByMemoNum(int memoNum) {
		return dao.getMemoVoByMemoId(memoNum);
	}
	
	/**
	 * 메모 삭제
	 * @param memoNum
	 * @return 1 일 때 메모 삭제 성공 0 일 때 실패
	 */
	@Override
	public int memoDelete(int memoNum) {
		return dao.deleteMemo(memoNum);
	}

	@Override
	public int count(String userId) throws Exception {
		return dao.count(userId);
	}

	@Override
	public List<MemoVo> listPage(String user, int displayPost, int postNum) throws Exception {
		return dao.listPage(user, displayPost, postNum);
	}

	@Override
	public List<MemoVo> listPageSearch(String user, int displayPost, int postNum, String searchType, String keyword) throws Exception {
		return dao.searchMemo(user, postNum, displayPost, searchType, keyword);
	}

	@Override
	public int listPageCount(String serachType, String keyword, String user) throws Exception {
		return dao.searchCount(serachType, keyword, user);
	}

	@Override
	public List<String> listUser(String userId) throws Exception {
		return dao.memoUserList(userId);
	}

	@Override
	public List<Integer> splitString(String memoNums) throws Exception {
		String[] memoArr =  memoNums.split(",");
		List<Integer> memoNum = new ArrayList<Integer>();

		for (int i =0;  i < memoArr.length; ++i ) {
			memoNum.add(Integer.parseInt(memoArr[i]));
		}
		
		return memoNum;
	}

	@Override
	public int memoShare(List<String> userList, List<Integer> memoList) throws Exception {
		int status = 1;
		
		for(int i = 0; i < userList.size(); ++i) {		
			for(int j = 0; j < memoList.size(); ++j) {
				SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
				Date current = new Date();
				String shareTime = dateFormat.format(current);
				int result = dao.memoShare(userList.get(i), memoList.get(j), shareTime);
				if(result == 0) {
					status = 0;
				}
			}
		}
		return status;
	}

	@Override
	public List<HashMap<String, Object>> unionPageSearch(String user, int displayPost, int postNum, String searchType, String keyword)
			throws Exception {
		
		List<HashMap<String, Object>> temp = dao.searchUnionMemo(user, postNum, displayPost, searchType, keyword);
		
		for(int i=0; i<temp.size(); ++i) {

			HashMap<String, Object> test = (HashMap<String, Object>) temp.get(i);
			try {
				if(((Integer) test.get("MEMO_SHARE_FAV")) == 1) {
					test.put("MEMO_FAV", 1);
				}
			
				if((Integer) test.get("MEMO_SHARE_FAV") == 0) {
					test.put("MEMO_FAV", 0);
				}
			
				temp.set(i, test);
			} catch (Exception e) {
				System.out.println("my memo");
			}
		}
		
		return temp;
	}

	@Override
	public Integer unionPageCount(String serachType, String keyword, String user) throws Exception {
		return dao.searchUnionCount(serachType, keyword, user);
	}

	@Override
	public List<String> selectSharedUser(Integer memoNum) throws Exception {
		return dao.selectSharedUser(memoNum);
	}

	@Override
	public Integer cancleShare(Integer memoNum, String userId) throws Exception {
		return dao.cancleShare(memoNum, userId);
	}

	@Override
	public Integer editFavSharedMemo(Integer memoNum, String userId, Integer fav) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("memoNum", memoNum);
		data.put("userId", userId);
		data.put("favor", fav);
		return dao.updateShareMemoFav(data);
	}

	@Override
	public Integer editFavMemo(Integer memoNum, Integer fav) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("memoNum", memoNum);
		data.put("favor", fav);
		return dao.updateMemoFav(data);
	}

	@Override
	public Integer deleteNotSharedMemo(Integer memoNum) throws Exception {
		
		return dao.deleteNotSharedMemo(memoNum);
	}

	// 메모 폼 추가
	@Override
	public Integer addForm(String userId, String formData, String formName) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("formName", formName);
		data.put("formData", formData);
		return dao.insertForm(data);
	}

	@Override
	public HashMap<String, Object> getOneForm(Integer formId) throws Exception {
		return dao.selectOneForm(formId);
	}

	@Override
	public List<HashMap<String, Object>> getUserForm(String UserId) throws Exception {
		return dao.selectUserForm(UserId);
	}

	@Override
	public Integer deleteForm(Integer formId) throws Exception {
		return dao.deleteForm(formId);
	}

	@Override
	public List<HashMap<String, Object>> getFavMemo(String userId) throws Exception {
		return dao.selectFavMemo(userId);
	}

	@Override
	public String getSuper() throws Exception {
		return dao.selectSuper();
	}

	@Override
	public Integer getSuperCount(String searchType, String keyword) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		keyword = '%' + keyword + '%';
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return dao.selectSuperCount(data);
	}

	@Override
	public List<HashMap<String, Object>> getSuperSearchedMemo(Integer displayPost, Integer postNum, String searchType, String keyword) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		keyword = '%' + keyword + '%';
		param.put("displayPost", displayPost);
		param.put("postNum", postNum);
		param.put("searchType", searchType);
		param.put("keyword", keyword);
		
		List<HashMap<String, Object>> temp = dao.selectSuperSearchedMemo(param);
		
		for(int i=0; i<temp.size(); ++i) {

			HashMap<String, Object> test = (HashMap<String, Object>) temp.get(i);
			try {
				if(test.get("MEMO_FAV").equals(true)) {
					test.put("MEMO_FAV", 1);
				}
			
				if(test.get("MEMO_FAV").equals(false)) {
					test.put("MEMO_FAV", 0);
				}
			
				temp.set(i, test);
			} catch (Exception e) {
				System.out.println("error");
			}
		}
		
		return temp;
	}
	
	
	
	

}

package com.itwinner.webmemo.memo.controller;

import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itwinner.webmemo.HomeController;
import com.itwinner.webmemo.memo.page.Page;
import com.itwinner.webmemo.memo.service.MemoService;
import com.itwinner.webmemo.memo.vo.MemoVo;

@Controller
public class MemoController {
	
	@Autowired
	MemoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * 메모 목록 GET
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/memoList", method = RequestMethod.GET)
	public ModelAndView memoList(ModelAndView mv) {
		mv.setViewName("memo/memo_list");
//		mv.addObject("memoList", service.memoSelect());
//		mv.addObject("memoList", service.userMemoSelect("test"));
		
		// TODO 세션에서 아이디 받기
		
		List<MemoVo> list = null;
		list = (List<MemoVo>) service.userMemoSelect("test");
		logger.info(list.toString());
		mv.addObject("memoList", list);
		return mv;
	}
	
	
	//페이징 된 리스트
	@RequestMapping(value="/memoListPage", method = RequestMethod.GET)
	public ModelAndView memoListPage(@RequestParam("num") int num, ModelAndView mv, HttpSession session) throws Exception {
		
		mv.setViewName("memo/memo_listPage");
		
		String user = (String) session.getAttribute("id");
		int count = service.count(user);
		
		//페이징
		
		Page page = new Page();
		page.setNum(num);
		page.setCount(count);
		
		List<MemoVo> list = null;
		list = (List<MemoVo>) service.listPage(user , page.getDisplayPost(), page.getPostNum());
		logger.info(list.toString());
		
		//페이지 출력 갯수
		int postNum = 10;
		
		mv.addObject("memoList", list);
		mv.addObject("page", page);
		mv.addObject("select", num);
		return mv;
	}
	
//	//검색 기능 추가 구현
//	@RequestMapping(value="/memoListPageSearch", method = RequestMethod.GET)
//	public ModelAndView memoListPageSearch(@RequestParam("num") int num,
//			@RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
//			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
//			ModelAndView mv, HttpSession session) throws Exception {
//		
//		mv.setViewName("memo/memo_listPageSearch");
//		
//		// 세션에서 아이디 받기
//		
//		String user = (String) session.getAttribute("id");
//		logger.info("user : " +  user);
//		Integer count = service.listPageCount(searchType, keyword, user);
//		logger.info(count.toString());
//		//페이징
//		
//		Page page = new Page();
//		page.setNum(num);
//		page.setCount(count);
//		page.setKeyword(keyword);
//		page.setSearchType(searchType);
//		List<MemoVo> list = null;
//		list = (List<MemoVo>) service.listPageSearch(user , page.getDisplayPost(), page.getPostNum(), searchType, keyword);
//		logger.info(list.toString());
//		
//		//페이지 출력 갯수
//		
//		//페이징 번호
////		int pageNum = (int)Math.ceil((double)count/postNum);
////		int displayPost = (num -1) * postNum;
//		
//		
//		mv.addObject("memoList", list);
//		mv.addObject("page", page);
//		mv.addObject("select", num);
//		mv.addObject("user", user);
//		
//		return mv;
//	}
	
	//검색 기능 추가 구현 및 union 적용
	@RequestMapping(value="/memoListPageSearch", method = RequestMethod.GET)
	public ModelAndView memoListPageSearch(@RequestParam("num") int num,
			@RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			ModelAndView mv, HttpSession session) throws Exception {
		
		mv.setViewName("memo/memo_listPageSearch");
		
		// 세션에서 아이디 받기
		
		String user = (String) session.getAttribute("id");
		
		String superUser = service.getSuper();
		logger.info("super user: "  + superUser);
	
		Page page = new Page();
		List<HashMap<String, Object>> list = null;
		
		if(user.equals(superUser)) { 
			
			Integer count = service.getSuperCount(searchType, keyword);
			logger.info("superuser Login");
			page.setNum(num);
			page.setCount(count);
			page.setKeyword(keyword);
			page.setSearchType(searchType);
			
			list = service.getSuperSearchedMemo(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
			if(list.equals(null)) {}
			else {
			logger.info(list.toString());
			}
		} else {
		
		
			logger.info("user : " +  user);
			Integer count = service.unionPageCount(searchType, keyword, user);
			logger.info("count : " + count.toString());
			//페이징
		

			page.setNum(num);
			page.setCount(count);
			page.setKeyword(keyword);
			page.setSearchType(searchType);
			list = (List<HashMap<String, Object>>) service.unionPageSearch(user , page.getDisplayPost(), page.getPostNum(), searchType, keyword);
			logger.info(list.toString());
		
		//페이지 출력 갯수
		
		//페이징 번호
//		int pageNum = (int)Math.ceil((double)count/postNum);
//		int displayPost = (num -1) * postNum;
		
		}
		
		mv.addObject("memoList", list);
		mv.addObject("page", page);
		mv.addObject("select", num);
		mv.addObject("user", user);
		
		return mv;
	}
	
	/**memoInsert
	 * 메모 작성  GET
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/memoWrite", method = RequestMethod.GET)
	public ModelAndView memoInsert(ModelAndView mv, HttpSession session) throws Exception {
		logger.info("getMethod called");
		
		String user = (String) session.getAttribute("id");
		mv.setViewName("memo/memo_write");
		List<HashMap<String, Object>> forms = service.getUserForm(user);
		
		logger.info(" forms : " + forms.toString());
		
		mv.addObject("forms", forms);
		
		return mv;
	}
	
	/**memoWrite
	 * 메모 작성 POST
	 * @param memoVo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/memoWrite", method = RequestMethod.POST)
	public ModelAndView memoWrite(MemoVo memoVo ,ModelAndView mv, HttpSession session) throws Exception {
		logger.info("post method called");
		
		//세션에서 user 받아오기
		String user = (String) session.getAttribute("id");
		memoVo.setUserId(user);
		
		//select문에서 마지막 문서 번호 찾아와서 밀어주는 code 작성
		mv.setViewName("memo/memo_write");
		
		int status = service.memoWrite(memoVo);
		logger.info(memoVo.toString()); //memoVo 찍어보기
		

		
		if(status == 1) {
		mv.setViewName("redirect:/memoListPageSearch?num=1");
		logger.info("write complete");
		return mv;
		} else {
			logger.info("error occured");
			return mv;
		}
	}
	
	/**memoDelete
	 * 메모 삭제 기능 GET
	 * @param mv
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/memoDelete", method = RequestMethod.GET)
	public ModelAndView memoDelete(@RequestParam("memoNum") int memoNum, ModelAndView mv, HttpSession session, HttpServletResponse rs) throws Exception {
		//TODO 세션 처리 기능
		
		String user = (String) session.getAttribute("id");
		
		String superUser = service.getSuper();
		int status = 0;
		if(user.equals(superUser)) {
		 	status = service.memoDelete(memoNum);
		} else {
		
			status = service.deleteNotSharedMemo(memoNum);
			try {
				JSONObject jso = new JSONObject();
				jso.put("status", status);
				rs.setContentType("text/html;charset=utf-8");
				PrintWriter out = rs.getWriter();
				out.print(jso);
				} catch(Exception e) {
				System.out.println("오류 발생 json");
			}
		}
		mv.setViewName("redirect:/memoListPageSearch?num=1");
		return mv;
	}
	
	// 삭제 기능 POST METHOD LIST에서 사용 함
	@ResponseBody
	@RequestMapping(value="/memoDelete", method = RequestMethod.POST)
	public void memoDeletePost(Integer memoNum, ModelAndView mv, HttpServletResponse response, HttpSession session) throws Exception {
		
		String user = (String) session.getAttribute("id");
		
		String superUser = service.getSuper();
		int status = 0;
		if(user.equals(superUser)) { 
			status = service.memoDelete(memoNum);
			
		} else {
		

		status = service.deleteNotSharedMemo(memoNum);
		logger.info("delete post Method");
		
		try {
		JSONObject jso = new JSONObject();
		jso.put("status", status);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jso);
		} catch(Exception e) {
			System.out.println("오류 발생 json");
		}

		}
	}
	
	
	//게시물 조회
	@RequestMapping(value="/memoView", method = RequestMethod.GET)
	public ModelAndView getView(@RequestParam("memoNum") int memoNum, ModelAndView mv, HttpSession session) {
		String user = (String) session.getAttribute("id");
		logger.info("viewGetMethod called" + "\n memoNumber: " + memoNum);
		MemoVo vo = service.getMemoVoByMemoNum(memoNum);
		logger.info(vo.toString());
		mv.addObject("user", user);
		mv.addObject("memo", vo);
		mv.setViewName("memo/memo_view");
		return mv;
	}
	
	//게시물 수정
	@RequestMapping(value="/memoUpdate", method = RequestMethod.GET)
	public ModelAndView memoUpdate(@RequestParam("memoNum") int memoNum, ModelAndView mv, HttpSession session) throws Exception {
		
		String user =  (String) session.getAttribute("id");
		
		logger.info("memoUpdate called" + "\n memoNumber: " + memoNum);
		MemoVo vo = service.getMemoVoByMemoNum(memoNum);
		logger.info(vo.toString());
		
		List<HashMap<String, Object>> forms = service.getUserForm(user);
		
		logger.info(" forms : " + forms.toString());
		
		mv.addObject("forms", forms);
		mv.addObject("memo", vo);
		mv.setViewName("memo/memo_modify");
		return mv;
	}
	//게시물 수정 POST 버튼
	@RequestMapping(value="/memoUpdate", method = RequestMethod.POST)
	public ModelAndView memoUpdate(@RequestParam("memoNum") int memoNum, MemoVo memoVo ,ModelAndView mv) throws Exception {
		logger.info("update post method called");
		memoVo.setMemoNum(memoNum);
		logger.info(memoVo.toString()); //memoVo 찍어보기
		int status = service.updateMemoByMemoVo(memoVo);
		logger.info("status = " + status);
		if (status==1) {
			logger.info("Update Complete");
			mv.setViewName("redirect:/memoListPageSearch?num=1");
		} else {
			logger.info(" update failed");
		}
		return mv;
	}
	
	//공유 팝업 창
	@RequestMapping(value="/memoUserPop", method = RequestMethod.GET)
	public ModelAndView memoUserPop(ModelAndView mv, @RequestParam("arr") String arr, HttpSession session) throws Exception {
		mv.setViewName("memo/memo_user_popUp");
		System.out.println(arr);
		
		List<Integer> memoNums = service.splitString(arr);
		System.out.println(memoNums.toString());
		
		//TODO 세션 처리
		String userId = (String) session.getAttribute("id");
		
		//TODO 내 메모 일 때만 작동하게 하기
		List<String> userList = service.listUser(userId);
		logger.info(userList.toString());
		mv.addObject("userList", userList);
		mv.addObject("memoList", memoNums);
		
		
		return mv;
	}
	
	//공유 POST METHOD
	@RequestMapping(value="/memoShare", method = RequestMethod.POST)
	@ResponseBody
	public void memoShare(
						@RequestParam(value="userList[]") List<String> userList,
						@RequestParam(value="memoList[]") List<Integer> memoList) throws Exception {
		
		logger.info(userList.toString());
		logger.info(memoList.toString());
		
		int status = service.memoShare(userList, memoList);
		
		//int status = service.memoShare(userId, Integer.parseInt(memoNum));
		
//		if(status == 1) {
//			System.out.println("share complete");
//		} else {
//			System.out.println("공유 실패");
//		}
	}
	// 공유 취소 GET
	@RequestMapping(value="/memoShareCanclePop", method = RequestMethod.GET)
	public ModelAndView memoShareCancle(ModelAndView mv, @RequestParam(value="memoNum") Integer memoNum) throws Exception {
		mv.setViewName("memo/memo_cancle_share");
		System.out.println("memoNum: " + memoNum );
		
		List<String> userList = null;
		
		userList = service.selectSharedUser(memoNum);
		mv.addObject("userList", userList);
		mv.addObject("memoNum", memoNum);
		
		return mv;
	}
	
	// 공유 취소 POST
	@RequestMapping(value="/memoShareCanclePop", method = RequestMethod.POST)
	@ResponseBody
	public void memoShareCancle(String userId, String memoNum) throws Exception {
		logger.info("공유 취소 POST --- " + userId + " " + Integer.parseInt(memoNum));
		
		//TODO mapper 매핑
		int status = service.cancleShare(Integer.parseInt(memoNum), userId);
		
		if (status == 1) {
			System.out.println("cancle share 성공");
		} else {
			System.out.println("cancle share 실패");
		}
	}
	
	// 공유 받은 메모 즐겨 찾기 취소
	@RequestMapping(value="/shareMemoCancleFav", method = RequestMethod.POST)
	@ResponseBody
	public void shareMemoCancleFav(String userId, String memoNum) throws Exception {
		logger.info("shareMemoCancleFav--" + userId + " " + Integer.parseInt(memoNum));
		
		int status = service.editFavSharedMemo(Integer.parseInt(memoNum), userId, 0);
		if(status == 1) {
			logger.info("shareMemoCanCleFav ----- 성공");
		} else {
			logger.info("memoCancleFav----- 실패");
		}
	}
	// 내 메모 즐겨 찾기 취소
	@RequestMapping(value="/memoCancleFav", method = RequestMethod.POST)
	@ResponseBody
	public void memoCancleFav(String memoNum) throws Exception {
		logger.info("memoCancleFav--" + Integer.parseInt(memoNum));
		
		int status =service.editFavMemo(Integer.parseInt(memoNum), 0);
		
		if(status == 1) {
			logger.info("memoCancleFav----- 성공");
		} else {
			logger.info("memoCancleFav----- 실패");
		}
	}
	
	//공유 받은 메모 즐겨 찾기 등록
	@RequestMapping(value="/shareMemoAddFav", method = RequestMethod.POST)
	@ResponseBody
	public void shareMemoAddFav(String userId, String memoNum) throws Exception {
		logger.info("shareMemoAddFav--" + userId + " " + Integer.parseInt(memoNum));
		
		int status = service.editFavSharedMemo(Integer.parseInt(memoNum), userId, 1);
		
		if(status == 1) {
			logger.info("shareMemoAddFav----- 성공");
		} else {
			logger.info("shareMemoAddFav----- 실패");
		}
		
	}
	// 내 메모 즐겨 찾기 등록
	@RequestMapping(value="/memoAddFav", method = RequestMethod.POST)
	@ResponseBody
	public void memoAddFav(String memoNum) throws Exception {
		logger.info("memoAddFav--" + Integer.parseInt(memoNum));
		
		int status = service.editFavMemo(Integer.parseInt(memoNum), 1);
		
		if(status == 1) {
			logger.info("memoAddFav----- 성공");
		} else {
			logger.info("memoAddFav----- 실패");
		}
	}
	
	// 메모 폼 등록 GET
	@RequestMapping(value="/memoFormAdd", method = RequestMethod.GET)
	public ModelAndView memoFormAdd(ModelAndView mv) {
		logger.info("getMethod called");
		mv.setViewName("memo/memo_form_add");
		return mv;
	}
	
	// 메모 폼 등록 POST
	@RequestMapping(value="/memoFormAdd", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView memoFormAdd(String formName, String formData, ModelAndView mv, HttpSession session) throws Exception {
		logger.info("post method called");
		
		//세션에서 user 받아오기
		String user = (String) session.getAttribute("id");
	
		
		//select문에서 마지막 문서 번호 찾아와서 밀어주는 code 작성
		mv.setViewName("memo/memo_form_add");
		logger.info(user + " " +  formName + "  " +  formData); //memoVo 찍어보기
		
		int status = service.addForm(user, formData, formName);

		
		if(status == 1) {
		mv.setViewName("redirect:/memoFormList");
		logger.info("formAdd complete");
		return mv;
		} else {
			logger.info("error occured");
			return mv;
		}
	}
	
	// 내 폼 리스트
	@RequestMapping(value="/memoFormList", method = RequestMethod.GET)
	public ModelAndView memoFormList(ModelAndView mv, HttpSession session) throws Exception {
		mv.setViewName("memo/memo_form_list");

		// TODO 세션에서 아이디 받기
		
		String user =  (String) session.getAttribute("id");
		
		List<HashMap<String, Object>> list = null;
		list =  service.getUserForm(user);
		logger.info(list.toString());
		mv.addObject("memoForm", list);
		return mv;
	}
	
	//내 폼 조회
	@RequestMapping(value="/formView", method = RequestMethod.GET)
	public ModelAndView memoFormView(@RequestParam("formId") int formId, ModelAndView mv, HttpSession session) throws Exception {
		String user = (String) session.getAttribute("id");
		logger.info("viewGetMethod called" + "\n memoNumber: " + formId);
		HashMap<String, Object> form = service.getOneForm(formId);
		logger.info(form.toString());
		mv.addObject("user", user);
		mv.addObject("form", form);
		mv.setViewName("memo/memo_form_view");
		return mv;
	}
	
	
	//내 폼 제거
	@ResponseBody
	@RequestMapping(value="/formDelete", method = RequestMethod.GET)
	public ModelAndView formDelete(@RequestParam("formId") int formId, ModelAndView mv, HttpSession session) throws Exception {
		
		int status = service.deleteForm(formId);
		logger.info("delete complete");
		mv.setViewName("redirect:/memoFormList");
		return mv;
	}
}

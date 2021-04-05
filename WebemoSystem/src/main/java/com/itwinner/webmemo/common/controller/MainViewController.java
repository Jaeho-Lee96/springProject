package com.itwinner.webmemo.common.controller;





import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itwinner.webmemo.HomeController;
import com.itwinner.webmemo.memo.service.MemoService;

@Controller
public class MainViewController {
	
	@Autowired
	MemoService service;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/Common/main", method = RequestMethod.GET)
	public ModelAndView mainviewGet(ModelAndView mv, HttpSession session) throws Exception {
		logger.info("enter mainview");
		
		String user = (String) session.getAttribute("id");
		
		List<HashMap<String, Object>> list = null;
		list =  service.getFavMemo(user);
		mv.addObject("memoList", list);
		
		logger.info(list.toString());
		
		return mv;
	}
	
	
	
}

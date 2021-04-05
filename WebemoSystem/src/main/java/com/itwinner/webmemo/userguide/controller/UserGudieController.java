package com.itwinner.webmemo.userguide.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwinner.webmemo.user.controller.UserController;

@Controller
public class UserGudieController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@RequestMapping(value = "/Common/user_guide", method = RequestMethod.GET)
	public String userGuide() {
		logger.info("user_guide");
		return "/Common/user_guide";
	}

}

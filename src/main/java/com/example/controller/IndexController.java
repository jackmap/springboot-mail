package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.MailService;

/**
 * @deprecated:
 * @author作者：mp 2019年6月11日
 */
@Controller
public class IndexController {
	@Autowired
	public MailService mailService;
	
	
	    @GetMapping("/index")
		public String Index() {
	    	mailService.sendSimpleMail("1541835901@qq.com", "测试邮件", "这是无所谓的结局和开始");
			return "index";
		}

}

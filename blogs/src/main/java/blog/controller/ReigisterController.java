package blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.service.UserService;


	@Controller
	public class ReigisterController{
		@Autowired
		private UserService accountService;
		
		//登陆画面显示
		@GetMapping("/register")
		public String getRegisterPage() {
			return "register.html";
		}
		
		//用户信息登陆
		@PostMapping("/register")
		public String register(@RequestParam String userName,
				@RequestParam String userEmail, 
				@RequestParam String password) {
			//如果保存过 迁移到login.html
			if(accountService.createAccount(userName,userEmail,password)) {
				return "redirect:/login";
			}else {
				//不是这种情况的话 迁移到register.html
				return "register.html";
			}
		}
	}
	


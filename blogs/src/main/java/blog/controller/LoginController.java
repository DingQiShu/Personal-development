package blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.entity.UserEntity;
import blog.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	HttpSession session;

	@GetMapping("/login")
	public String getLogin() {
		return "Login.html";
	}

	@PostMapping("/login")
	public String adminAuth(@RequestParam String userEmail, @RequestParam String password, Model model) {
		// adminServiceクラスのfindByAdminEmailAndPasswordメソッドを使用して、該当するユーザー情報を取得する。
		UserEntity userEntity = userService.findByUserEmailAndPassword(userEmail, password);
		// adminEntity == null
		if (userEntity == null) {
			// admin_login.htmlが表示される。
			return "Login.html";
		} else {
			// adminEntityの内容をsessionに保存する
			session.setAttribute("user", userEntity);
			return "redirect:/blog/all";
		}
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";

	}

}

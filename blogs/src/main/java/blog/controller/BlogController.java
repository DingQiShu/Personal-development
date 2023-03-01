package blog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.entity.BlogEntity;
import blog.model.entity.UserEntity;
import blog.service.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller

public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired // 提取bean
	HttpSession session;

	@GetMapping("/blog/all")

	public String getBlog(Model model) {
		// セッションを使用してログインしている人の情報を取得する
		UserEntity user = (UserEntity) session.getAttribute("user");
		// 変数名：userからログインしている人のuserIdを取得する
		Long userId = user.getUserId();
		List<BlogEntity> blogList = blogService.selectByUserId(userId);
		model.addAttribute("blogList", blogList);

//		model.addAttribute("userName", userName);
		return "blog_list.html";
	}

	@GetMapping("/blog/register")
	public String getBlogRegister() {
		return "blog_register.html";
	}

	@PostMapping("/blog/register")
	public String register(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam String message,
			@RequestParam(name = "registerDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registerDate) {
		// セッションを使用してログインしている人の情報を取得する
		UserEntity user = (UserEntity) session.getAttribute("user");
		// 変数名：userからログインしている人のuserIdを取得する
		Long userId = user.getUserId();
		if (blogService.createBlog(blogTitle, categoryName, message, userId, registerDate)) {
			return "redirect:/blog/all";
		} else {
			// 不是这种情况的话 迁移到register.html
			return "blog_register.html";
		}
	}

	@GetMapping("/blog/detail/{blogId}")
	public String getBlogDetailPage(@PathVariable Long blogId, Model model) {
		BlogEntity blogList = blogService.selectByBlogId(blogId);
		model.addAttribute("blogList", blogList);
		return "blog_detail.html";

	}

	@GetMapping("/blog/edit/{blogId}")
	public String getBlogeditPage(@PathVariable Long blogId, Model model) {
		BlogEntity blogList = blogService.selectByBlogId(blogId);
		model.addAttribute("blogList", blogList);
		return "blog_edit.html";

	}

	@PostMapping("/blog/update")
	public String blogUpDate(@RequestParam String blogTitle, @RequestParam String message, @RequestParam Long blogId) {
		blogService.updateBlog(blogTitle, message, blogId);
		return "redirect:/blog/all";

	}

	@PostMapping("/blog/delete")
	public String blogDelete(@RequestParam Long blogId) {
		blogService.deleteBlog(blogId);
		return "redirect:/blog/all";
	}

}

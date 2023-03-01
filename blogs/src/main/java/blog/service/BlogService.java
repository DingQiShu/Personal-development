package blog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.model.dao.BlogDao;
import blog.model.entity.BlogEntity;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public boolean createBlog(String blogTitle, String categoryName, String message, Long userId,
			LocalDate registerDate) {
		// 从registerController传递的信息 DB搜索
		BlogEntity blogEntity = blogDao.findByBlogTitle(blogTitle);
		// 从registerController传递的信息 DB搜索的结果
		// 没有的话 保存
		if (blogEntity == null) {
			blogDao.save(new BlogEntity(blogTitle, categoryName, message, userId, registerDate));

			return true;

		} else {
			return false;
		}
	}
	//プロック一覧
	public List<BlogEntity> selectByUserId(Long userId){
		return blogDao.findByUserId(userId);
	}
	
	//プロック一覧
		public BlogEntity selectByBlogId(Long blogId){
			return blogDao.findByBlogId(blogId);
		}
		
		public void updateBlog(String blogTitle,String message,Long blogId) {
			BlogEntity blogEntity = blogDao.findByBlogId(blogId);
			blogEntity.setBlogTitle(blogTitle);
			blogEntity.setMessage(message);
			blogDao.save(blogEntity);
		}
		
		public List<BlogEntity>deleteBlog(Long blogId){
			return blogDao.deleteByBlogId(blogId);
		}
		
		
} 

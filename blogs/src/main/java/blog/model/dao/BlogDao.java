package blog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import blog.model.entity.BlogEntity;
import jakarta.transaction.Transactional;

public interface BlogDao extends JpaRepository<BlogEntity, Long> {
	// 从userService传递的用户信息 DB搜索
	BlogEntity findByBlogTitle(String blogTitle);

	// 从userService传递的用户信息 DB保存
	BlogEntity save(BlogEntity blogEntity);

	
	@Query(value = "SELECT b.blog_id,b.blog_title,b.category_name,b.message,b.register_date,b.user_id From blog b INNER JOIN account a ON b.user_id = a.user_id WHERE b.user_id=?1", nativeQuery = true)
	List<BlogEntity> findByUserId(Long userId);
//query转入sql语句 
	BlogEntity findByBlogId(Long blogId);
	
	@Transactional
	List<BlogEntity>deleteByBlogId(Long blogId);
	
	
}

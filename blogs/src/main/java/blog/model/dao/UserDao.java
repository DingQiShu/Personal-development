package blog.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.model.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
//从userService传递的用户信息 DB搜索
	UserEntity findByUserEmail(String userEmail);
	
	//从userService传递的用户信息 DB保存
	UserEntity save(UserEntity userEntity);
	
	//获取用户情报一览
	List<UserEntity> findAll();

	List<UserEntity> findByUserEmailAndPassword(String userEmail, String password);

	
}

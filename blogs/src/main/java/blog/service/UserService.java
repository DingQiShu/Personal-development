package blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.model.dao.UserDao;
import blog.model.entity.UserEntity;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	// ログイン処理
	public UserEntity findByUserEmailAndPassword(String userEmail, String password) {
		// コントローラークラスからadminEmailとpasswordと受け取って結果を受け取る
		List<UserEntity> userList = userDao.findByUserEmailAndPassword(userEmail, password);
		// もしadminListが空だった場合には、nullを返す処理
		if (userList.isEmpty()) {
			return null;
		} else {
			// もしadminListが空でなかった場合には、Ｌｉｓｔの0番目の要素を取得する
			return userList.get(0);
		}

	}

	// 保存用户信息
	public boolean createAccount(String userName, String userEmail, String password) {
		// 从registerController传递的信息 DB搜索
		UserEntity userEntity = userDao.findByUserEmail(userEmail);
		// 从registerController传递的信息 DB搜索的结果
		// 没有的话 保存
		if (userEntity == null) {
			userDao.save(new UserEntity(userName, userEmail, password));
			return true;

		} else {
			return false;
		}
	}

	// 用户一览取得
	public List<UserEntity> getAllAccounts() {
		return userDao.findAll();
	}

	// 为了找到ID

	public UserEntity selectById(String userEmail) {
		return userDao.findByUserEmail(userEmail);
	}
}

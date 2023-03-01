package blog.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity //アノテーションを付与すると、Springの機能により　当該クラスはEntityとして振る舞いようになります
		//Entity注解 告诉Spring该类对应数据库中的一个表
@Data	
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "account")
public class UserEntity {
	@Id 	//id类 必须使用 @ id注解标出
	@Column(name = "user_id")  //Column 注解手动设置列名
	@GeneratedValue(strategy = GenerationType.AUTO) //idフィールドの振る舞いを指定します
													//为实体生成唯一一个标识的主键
	private Long userId;

	@NonNull  //验证不是null
	@Column(name = "user_name")
	private String userName;

	@NonNull
	@Column(name = "user_email")
	private String userEmail;

	@NonNull
	@Column(name = "password")
	private String password;
}

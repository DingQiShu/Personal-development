package blog.model.entity;

import java.time.LocalDate;



import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="blog")
public class BlogEntity {
	
	@Id
	@Column(name= "blog_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long blogId;
	
	
	
	@NonNull
	@Column(name="blog_title")
	private String blogTitle;

	
	@NonNull
	@Column(name="category_name")
	private String categoryName;
	
	@NonNull
	@Column(name="message")
	private String message;
	
	
	@Column(name="user_id")
	private Long userId;
	
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "register_date")
	private LocalDate registerDate;

	public BlogEntity(@NonNull String blogTitle, @NonNull String categoryName, @NonNull String message, Long userId,
			@NonNull LocalDate registerDate) {
	
		this.blogTitle = blogTitle;
		this.categoryName = categoryName;
		this.message = message;
		this.userId = userId;
		this.registerDate = registerDate;
	}
	
	
	
}

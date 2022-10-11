package site.metacoding.miniproject.domain.category;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.PersonalJoinDto;

@NoArgsConstructor
@Getter
@Setter
public class Category {
	private Integer categoryId;
	private Boolean categoryFrontend;
	private Boolean categoryBackend;
	private Boolean categoryDevops;
	private Timestamp createdAt;
	
	// 회원가입 - 개인
	public Category(PersonalJoinDto personalJoinDto) {
		this.categoryFrontend = personalJoinDto.getCategoryFrontend();
		this.categoryBackend = personalJoinDto.getCategoryBackend();
		this.categoryDevops = personalJoinDto.getCategoryDevops();
	}	
}

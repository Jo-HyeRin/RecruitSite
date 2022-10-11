package site.metacoding.miniproject.domain.personal;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.PersonalJoinDto;

@NoArgsConstructor
@Getter
@Setter
public class Personal {
	private Integer personalId;
	private String personalName;
	private Integer personalCategoryId;
	private Timestamp createdAt;
	
	// 회원가입 - 개인
	public Personal(PersonalJoinDto personalJoinDto) {
		this.personalName = personalJoinDto.getPersonalName();
	}
}

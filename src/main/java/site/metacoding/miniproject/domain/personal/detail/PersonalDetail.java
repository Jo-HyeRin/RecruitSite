package site.metacoding.miniproject.domain.personal.detail;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.PersonalJoinDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalDetail {
	private Integer personalDetailId;
	private Integer personalId;
	private String personalEmail;
	private String personalPhoneNumber;
	private Timestamp createdAt;
	
	// 회원가입 - 개인
	public PersonalDetail(PersonalJoinDto personalJoinDto) {
		this.personalId = personalJoinDto.getPersonalId();
		this.personalPhoneNumber = personalJoinDto.getPersonalPhoneNumber();
		this.personalEmail = personalJoinDto.getPersonalEmail();
	}
}

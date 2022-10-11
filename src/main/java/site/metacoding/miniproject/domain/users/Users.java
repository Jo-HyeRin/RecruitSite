package site.metacoding.miniproject.domain.users;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.request.CompanyJoinDto;
import site.metacoding.miniproject.web.request.PersonalJoinDto;

@NoArgsConstructor
@Getter
@Setter
public class Users {
	private Integer usersId;
	private String loginId;
	private String loginPassword;
	private Integer personalId;
	private Integer companyId;
	private Timestamp createdAt;
	
	public Users(PersonalJoinDto personalJoinDto) {
		this.loginId = personalJoinDto.getLoginId();
		this.loginPassword = personalJoinDto.getLoginPassword();
		this.personalId = personalJoinDto.getPersonalId();
	}

	public Users(CompanyJoinDto companyJoinDto) {
		this.loginId = companyJoinDto.getLoginId();
		this.loginPassword = companyJoinDto.getLoginPassword();
		this.companyId = companyJoinDto.getCompanyId();
	}
}

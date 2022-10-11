package site.metacoding.miniproject.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginDto {
	private String loginId;
	private String loginPassword;

	public LoginDto(PersonalJoinDto personalJoindto) {
		this.loginId = personalJoindto.getLoginId();
		this.loginPassword = personalJoindto.getLoginPassword();
	}

	public LoginDto(CompanyJoinDto companyJoindto) {
		this.loginId = companyJoindto.getLoginId();
		this.loginPassword = companyJoindto.getLoginPassword();
	}

}

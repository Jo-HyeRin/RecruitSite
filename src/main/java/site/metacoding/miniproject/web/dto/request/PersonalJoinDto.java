package site.metacoding.miniproject.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonalJoinDto {
	private String loginId;
	private String loginPassword;
	private String personalName;
	private String personalEmail;
	private String personalPhoneNumber;;
	private Boolean categoryFrontend;
	private Boolean categoryBackend;
	private Boolean categoryDevops;

	// Users 계정 생성용
	private Integer personalId;
    private Integer categoryId;
}

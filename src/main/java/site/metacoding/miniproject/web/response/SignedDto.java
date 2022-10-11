package site.metacoding.miniproject.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignedDto<T> {
	private String loginId;
	private String loginPassword;
	private Integer personalId;
	private Integer companyId;
	private T Userinfo;
}

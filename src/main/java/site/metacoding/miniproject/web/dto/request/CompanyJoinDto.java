package site.metacoding.miniproject.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyJoinDto {
    private String loginId;
    private String loginPassword;
    private String companyName;
    private String companyEmail;
    private String companyAddress;
    private String companyPhoneNumber;

    // 계정생성용
    private Integer companyId;
}

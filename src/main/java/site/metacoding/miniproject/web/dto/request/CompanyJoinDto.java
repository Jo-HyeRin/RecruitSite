package site.metacoding.miniproject.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyJoinDto {
    private String loginId;
    private String loginPassword;
    private String companyName;
    private String companyPhoneNumber;
    private String companyEmail;
    private String companyPicture;
    private String companyAddress;

    // 계정생성용
    private Integer companyId;
}

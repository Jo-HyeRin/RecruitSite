package site.metacoding.miniproject.domain.company.detail;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.CompanyJoinDto;

@NoArgsConstructor
@Getter
@Setter
public class CompanyDetail {
	private Integer companyDetailId;
	private Integer companyId;
	private String companyEmail;
	private String companyPhoneNumber;
	private String companyPicture;
	private String companyAddress;
	private Timestamp createdAt;
	
	public CompanyDetail(CompanyJoinDto companyJoinDto) {
		this.companyId = companyJoinDto.getCompanyId();
		this.companyEmail = companyJoinDto.getCompanyEmail();
		this.companyPhoneNumber = companyJoinDto.getCompanyPhoneNumber();
		this.companyPicture = companyJoinDto.getCompanyPicture();
		this.companyAddress = companyJoinDto.getCompanyAddress();
	}
}

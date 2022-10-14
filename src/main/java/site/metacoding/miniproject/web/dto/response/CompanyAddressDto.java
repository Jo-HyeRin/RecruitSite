package site.metacoding.miniproject.web.dto.response;

import lombok.Getter;

@Getter
public class CompanyAddressDto {
	private Integer companyId;
	private Integer companyDetailId;
	
	private String zipCode;
	private String roadName;
	private String detailAddress;
}

package site.metacoding.miniproject.domain.company.detail;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDetail {
	private Integer companyDetailId;
	private Integer companyId;
	private String companyEmail;
	private String companyPhoneNumber;
	private String companyPicture;
	private String companyAddress;
	private String location;
	private Timestamp createdAt;
}

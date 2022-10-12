package site.metacoding.miniproject.domain.company;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.miniproject.web.dto.request.CompanyJoinDto;

@NoArgsConstructor
@Getter
@Setter
public class Company {
	private Integer companyId;
	private String companyName;
	private Timestamp createdAt;
	
	public Company(CompanyJoinDto companyJoinDto) {
		this.companyName = companyJoinDto.getCompanyName();
	}
}

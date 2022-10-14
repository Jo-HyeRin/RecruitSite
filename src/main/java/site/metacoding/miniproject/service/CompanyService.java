package site.metacoding.miniproject.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.company.detail.CompanyDetailDao;
import site.metacoding.miniproject.web.dto.response.CompanyAddressDto;

@RequiredArgsConstructor
@Service
public class CompanyService {

	private final CompanyDetailDao companyDetailDao;
	
	// 회사 주소 얻기
	public CompanyAddressDto findByAddress(Integer companyId) {
		return companyDetailDao.findByAddress(companyId);
	}


}
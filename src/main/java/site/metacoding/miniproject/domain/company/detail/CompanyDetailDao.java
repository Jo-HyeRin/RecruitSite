package site.metacoding.miniproject.domain.company.detail;

import java.util.List;

import site.metacoding.miniproject.web.dto.response.CompanyAddressDto;

public interface CompanyDetailDao {
	public void insert(CompanyDetail companyDetail);
	public List<CompanyDetail> findAll();
	public CompanyDetail findById(Integer id);
	public void update(CompanyDetail companyDetail);
	public void deleteById(Integer id);
	
	// 주소 정보 받기
	public CompanyAddressDto findByAddress(Integer companyId);
}

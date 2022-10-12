package site.metacoding.miniproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.category.Category;
import site.metacoding.miniproject.domain.category.CategoryDao;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.company.detail.CompanyDetail;
import site.metacoding.miniproject.domain.company.detail.CompanyDetailDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.personal.detail.PersonalDetail;
import site.metacoding.miniproject.domain.personal.detail.PersonalDetailDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.web.dto.request.CompanyJoinDto;
import site.metacoding.miniproject.web.dto.request.LoginDto;
import site.metacoding.miniproject.web.dto.request.PersonalJoinDto;
import site.metacoding.miniproject.web.dto.response.SignedDto;

@RequiredArgsConstructor
@Service
public class UsersService {

	private final UsersDao usersDao;
	private final PersonalDao personalDao;
	private final CompanyDao companyDao;
	private final PersonalDetailDao personalDetailDao;
	private final CompanyDetailDao companyDetailDao;
	private final CategoryDao categoryDao;

// 로그인 ============================== //
	public SignedDto<?> login(LoginDto loginDto) {

		String loginId = loginDto.getLoginId();
		String loginPassword = loginDto.getLoginPassword();
		SignedDto<?> signedDto;		
		Users users = usersDao.findByIdAndPassword(loginId, loginPassword);

		if (users == null) {
			return null;
		}

		if (users.getPersonalId() != null) {
			Personal personal = personalDao.findById(users.getPersonalId());
			signedDto = new SignedDto<>(users.getLoginId(),
					users.getLoginPassword(), personal.getPersonalId(), null, personal);
		} else {
			Company company = companyDao.findById(users.getCompanyId());
			signedDto = new SignedDto<>(users.getLoginId(),
					users.getLoginPassword(), null, company.getCompanyId(), company);
		}

		return signedDto;
	}
	
	
// 회원가입 - 개인 ============================== //
	@Transactional(rollbackFor = RuntimeException.class)
	public void joinPersonal(PersonalJoinDto personalJoinDto) {
		// 가장 기본이 되는 키를 가진 테이블을 가장 나중에 !
		
		Category category = new Category(personalJoinDto);
		categoryDao.insert(category);

		Personal personal = new Personal(personalJoinDto);
		personal.setPersonalCategoryId(category.getCategoryId());
		personalDao.insert(personal);

		Integer personalId = personal.getPersonalId();
		personalJoinDto.setPersonalId(personalId);

		PersonalDetail personalDetail = new PersonalDetail(personalJoinDto);
		personalDetailDao.insert(personalDetail);

		Users users = new Users(personalJoinDto);
		usersDao.insert(users);
	}

// 회원가입 - 기업 ============================== //
	@Transactional(rollbackFor = RuntimeException.class)
	public void joinCompany(CompanyJoinDto companyJoinDto) {

		Company company = new Company(companyJoinDto);
		companyDao.insert(company);

		Integer companyId = company.getCompanyId();
		companyJoinDto.setCompanyId(companyId);

		CompanyDetail companyDetail = new CompanyDetail(companyJoinDto);
		companyDetailDao.insert(companyDetail);

		Users users = new Users(companyJoinDto);
		usersDao.insert(users);
	}
	
// 아이디 중복 체크 ============================== //
	public Integer checkUsersId(String loginId) {
		Integer checkUsers = usersDao.findByLoginId(loginId);
		return checkUsers;
	}
	
}
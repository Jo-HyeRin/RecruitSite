package site.metacoding.miniproject.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.domain.company.Company;
import site.metacoding.miniproject.domain.company.CompanyDao;
import site.metacoding.miniproject.domain.personal.Personal;
import site.metacoding.miniproject.domain.personal.PersonalDao;
import site.metacoding.miniproject.domain.users.Users;
import site.metacoding.miniproject.domain.users.UsersDao;
import site.metacoding.miniproject.web.dto.request.LoginDto;
import site.metacoding.miniproject.web.dto.response.SignedDto;

@RequiredArgsConstructor
@Service
public class UsersService {

	private final UsersDao usersDao;
	private final PersonalDao personalDao;
	private final CompanyDao companyDao;

// 로그인 ============================== //
	public SignedDto<?> login(LoginDto loginDto) {

		String loginId = loginDto.getLoginId();
		String loginPassword = loginDto.getLoginPassword();
		SignedDto<?> signedDto;		
		Users userinfo = usersDao.findByIdAndPassword(loginId, loginPassword);

		if (userinfo == null) {
			return null;
		}

		if (userinfo.getPersonalId() != null) {
			Personal personal = personalDao.findById(userinfo.getPersonalId());
			signedDto = new SignedDto<>(userinfo.getLoginId(),
					userinfo.getLoginPassword(), personal.getPersonalId(), null, personal);
		} else {
			Company company = companyDao.findById(userinfo.getCompanyId());
			signedDto = new SignedDto<>(userinfo.getLoginId(),
					userinfo.getLoginPassword(), null, company.getCompanyId(), company);
		}

		return signedDto;
	}

}
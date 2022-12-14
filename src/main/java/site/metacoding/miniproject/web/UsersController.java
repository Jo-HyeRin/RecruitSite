package site.metacoding.miniproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.service.UsersService;
import site.metacoding.miniproject.web.dto.ResponseDto;
import site.metacoding.miniproject.web.dto.request.CompanyJoinDto;
import site.metacoding.miniproject.web.dto.request.LoginDto;
import site.metacoding.miniproject.web.dto.request.PersonalJoinDto;
import site.metacoding.miniproject.web.dto.response.SignedDto;

@RequiredArgsConstructor
@Controller
public class UsersController {

	private final UsersService usersService;
	private final HttpSession session;

// 로그인 ============================== //
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/personal/login";
	}

	@PostMapping("/login")
	public @ResponseBody ResponseDto<?> login(@RequestBody LoginDto loginDto) {
		SignedDto<?> signedDto = usersService.login(loginDto);
		if (signedDto == null)
			return new ResponseDto<>(-1, "로그인실패", null);
		session.setAttribute("principal", signedDto);
		return new ResponseDto<>(1, "로그인완료", session.getAttribute("principal"));
	}	
	
// 로그아웃 ============================== //
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "/index"; // 추후변경예정
	}
	
// 회원가입 - 개인 ============================== //
	@GetMapping("/joinPersonal")
	public String joinForm() {
		return "/personal/join";
	}

	@PostMapping("/join/personal")
	public @ResponseBody ResponseDto<?> joinPersonal(@RequestBody PersonalJoinDto personalJoinDto) {
		usersService.joinPersonal(personalJoinDto);
		LoginDto loginDto = new LoginDto(personalJoinDto);
		SignedDto<?> signedDto = usersService.login(loginDto);
		session.setAttribute("principal", signedDto);
		return new ResponseDto<>(1, "계정생성완료", session.getAttribute("principal"));
	}
	
	// 아이디 중복체크
	@GetMapping("/checkId/{loginId}")
	public @ResponseBody ResponseDto<?> usersIdSameCheck(@PathVariable String loginId) {

		ResponseDto<?> responseDto;

		if (loginId == null || loginId == "") {
			responseDto = new ResponseDto<>(-1, "아이디를 입력하여 주세요", null);
			return responseDto;
		}

		Integer usersCheck = usersService.checkUsersId(loginId);
		if (usersCheck == null) {
			responseDto = new ResponseDto<>(1, "아이디 중복 없음 사용하셔도 좋습니다.", null);
		} else {
			responseDto = new ResponseDto<>(-1, "아이디 중복임 사용 불가합니다", null);
		}
		return responseDto;
	}
	
// 회원가입 - 기업 ============================== //
	@GetMapping("/joinCompany")
	public String companyJoinForm() {
		return "company/join";
	}
	
	@PostMapping("/join/company")
	public @ResponseBody ResponseDto<?> conpanyJoin(@RequestBody CompanyJoinDto companyJoinDto) {
		usersService.joinCompany(companyJoinDto);
		LoginDto loginDto = new LoginDto(companyJoinDto);
		SignedDto<?> signedDto = usersService.login(loginDto);
		session.setAttribute("principal", signedDto);
		return new ResponseDto<>(1, "계정생성완료", session.getAttribute("principal"));
	}
	
}

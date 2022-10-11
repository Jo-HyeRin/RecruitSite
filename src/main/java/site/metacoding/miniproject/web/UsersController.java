package site.metacoding.miniproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.service.UsersService;
import site.metacoding.miniproject.web.dto.ResponseDto;
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
	
}

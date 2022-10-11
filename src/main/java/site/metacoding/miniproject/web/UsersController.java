package site.metacoding.miniproject.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.service.UsersService;
import site.metacoding.miniproject.web.request.LoginDto;
import site.metacoding.miniproject.web.response.SignedDto;

@RequiredArgsConstructor
@Controller
public class UsersController {
	
	private final UsersService usersService;
	private final HttpSession session;

	// 로그인==============================================
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/login";
	}
	
	@PostMapping("/login")
	public @ResponseBody ResponseDto<?> login(@RequestBody LoginDto loginDto) {
		SignedDto<?> signedDto = usersService.login(loginDto);
		if (signedDto == null)
			return new ResponseDto<>(-1, "로그인실패", null);
		session.setAttribute("principal", signedDto);
		return new ResponseDto<>(1, "로그인완료", session.getAttribute("principal"));
	}	
	
	// 로그아웃=============================================
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "/";
	}
}

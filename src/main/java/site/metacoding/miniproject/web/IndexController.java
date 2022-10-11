package site.metacoding.miniproject.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@Value("${pic.path}")
	// properties 에 설정한 경로 불러오기
	// import org.springframework.beans.factory.annotation.Value !!!
	private String uploadUrl;	
		
	@PostMapping("/upload")
	public @ResponseBody String uploadPicture(@RequestPart MultipartFile file) {
		// 파일 이름 불러오기
		String fileName = file.getOriginalFilename();
		// System.out.println("fileName : "+fileName);
		
		// 받은 file을 어디에 저장하나? (경로 지정 - 불러온 설정 경로 넣어주기)
		String filePath = uploadUrl + fileName;
		// System.out.println(uploadUrl);
		
		// 파일 경로 담은 객체 만들기
		File dest = new File(filePath);
		try {
			Files.copy(file.getInputStream(), dest.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "ok";
	}
	
}
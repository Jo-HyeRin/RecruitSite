package site.metacoding.miniproject.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
		
	@PostMapping("/upload")
	public @ResponseBody String uploadPicture(@RequestPart MultipartFile file) {
		// 파일 이름 불러오기
		String fileName = file.getOriginalFilename();
		// System.out.println("fileName : "+fileName);
		
		// 받은 file을 어디에 등록하나? (하드디스크에 저장하려면 경로 폴더 지정)
		String filePath = "C:\\Users\\GGG\\git\\RecruitSite\\src\\main\\resources\\static\\img\\" + fileName;
		// System.out.println("filePath"+filePath);
		
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
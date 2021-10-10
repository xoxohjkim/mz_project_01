package mz.controller;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private static final String PATH = "resources" + File.separator + "upload" + File.separator + "img";
	
	@ResponseBody
	@PostMapping("/file-upload")
	public void fileUpload(List<MultipartFile> multipartFile) {
		System.out.println(multipartFile);
	}
	
}

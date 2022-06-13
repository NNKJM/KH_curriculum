package com.spring.mybatis04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {
	
	@Autowired
	private Upload upload;
	
	@RequestMapping("upload.do")
	public String upload() {
		return "uploadForm";
	}

	@RequestMapping("upload_ok.do")
	public String upload_ok(MultipartHttpServletRequest uRequest, Model model) {
		if(upload.fileUpload(uRequest)) {
			model.addAttribute("result", "파일 업로드 성공");
		} else {
			model.addAttribute("result", "파일 업로드 실패");
		}
		return "upload_result";
	}
}

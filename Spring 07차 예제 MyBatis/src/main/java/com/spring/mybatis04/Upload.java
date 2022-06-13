package com.spring.mybatis04;

import java.io.File;
import java.util.Calendar;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class Upload {
	
	public boolean fileUpload(MultipartHttpServletRequest mRequest) {
		boolean isUpload = false;
		String uploadPath = "C:\\NCS\\workspace(spring)\\MyBatis04\\src\\main\\resources\\upload";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext()) {
			try {
				String uploadFileName = iterator.next();
				MultipartFile mFile = mRequest.getFile(uploadFileName);
				
				String originaleFileName = mFile.getOriginalFilename(); // 업로드한 파일의 이름을 구하는 메서드
				String homedir = uploadPath + year + "-" + month + "-" + day; // 실제 들어갈 폴더
				File path1 = new File(homedir);
				if(!path1.exists()) {
					path1.mkdirs();
				}
				String saveFileName = originaleFileName;
				if(saveFileName != null) {
					saveFileName = System.currentTimeMillis() + "_" + saveFileName;
				}
				File origin = new File(homedir + "/" + saveFileName);
					mFile.transferTo(origin);
					isUpload = true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return isUpload;
	}

}

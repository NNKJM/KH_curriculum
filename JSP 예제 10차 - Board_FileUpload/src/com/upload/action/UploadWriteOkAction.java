package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String savefolder = "C:\\NCS\\workspace(jsp)\\16_Board_FileUpload\\WebContent\\WEB-INF\\upload";
		int fileSize = 10 * 1024 * 1024;
		UploadDTO dto = new UploadDTO();
		
		MultipartRequest multi = new MultipartRequest(request, savefolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String upload_writer = multi.getParameter("upload_writer").trim();
		String upload_title = multi.getParameter("upload_title").trim();
		String upload_content = multi.getParameter("upload_content").trim();
		String upload_pwd = multi.getParameter("upload_pwd").trim();
		
		File upload_file = multi.getFile("upload_file");
		if(upload_file != null) {
			String fileName = upload_file.getName();
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			String homedir = savefolder+"/"+year+"-"+month+"-"+day;
			File path1 = new File(homedir);
			if(!path1.exists()) { // 폴더가 없을 시
				path1.mkdir();
			}
			String reFileName = upload_writer+"_"+fileName;
			upload_file.renameTo(new File(homedir+"/"+reFileName));
			
			String fileDBName = "/"+year+"-"+month+"-"+day+"/"+reFileName;
			dto.setUpload_file(fileDBName);
			System.out.println("DB Name : " + fileDBName);
		}
		dto.setUpload_writer(upload_writer);
		dto.setUpload_title(upload_title);
		dto.setUpload_cont(upload_content);
		dto.setUpload_pwd(upload_pwd);
		UploadDAO dao = UploadDAO.getInstance();
		int check = dao.insertUpload(dto);
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		} else {
			out.println("<script>");
			out.println("alert('자료실 업로드 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}

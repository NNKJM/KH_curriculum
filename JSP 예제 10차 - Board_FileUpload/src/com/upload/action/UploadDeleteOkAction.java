package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pwd = request.getParameter("pwd").trim();
		int no = Integer.parseInt(request.getParameter("no").trim());
		
		UploadDAO dao = UploadDAO.getInstance();
		UploadDTO dto = dao.uploadContent(no);
		String upload = "C:\\NCS\\workspace(jsp)\\16_Board_FileUpload\\WebContent\\WEB-INF\\upload";
		String fileName = dto.getUpload_file();
		int check = 0;
		
		if(pwd.equals(dto.getUpload_pwd())) {
			check = dao.deleteUpload(no, pwd);
			if(fileName != null) {
				File file = new File(upload + fileName);
				file.delete();
			}
		}
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		} else if(check==-1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}

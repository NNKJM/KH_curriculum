package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		UploadDAO dao = UploadDAO.getInstance();
		dao.uploadHit(bbs_no);
		UploadDTO dto = dao.uploadContent(bbs_no);
		
		request.setAttribute("upCont", dto);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/upload_content.jsp");
		
		return forward;
	}

}

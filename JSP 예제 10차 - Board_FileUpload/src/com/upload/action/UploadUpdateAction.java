package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		UploadDAO dao = UploadDAO.getInstance();
		UploadDTO dto = dao.uploadContent(upload_no);

		request.setAttribute("upCont", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/upload_update.jsp");
		
		return forward;
	}

}

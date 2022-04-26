package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		BbsDAO dao = BbsDAO.getInstance();
		dao.boardHit(bbs_no);
		
		BbsDTO dto = dao.getBbsContent(bbs_no);
		
		request.setAttribute("Cont", dto);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/bbs_content.jsp");
		
		return forward;
	}

}

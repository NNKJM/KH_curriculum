package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bbs_writer = request.getParameter("writer").trim();
		String bbs_title = request.getParameter("title").trim();
		String bbs_content = request.getParameter("content").trim();
		String bbs_pwd = request.getParameter("pwd").trim();
		
		int bbs_no = Integer.parseInt(request.getParameter("bbs_no").trim());
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_no(bbs_no);
		dto.setBoard_writer(bbs_writer);
		dto.setBoard_title(bbs_title);
		dto.setBoard_cont(bbs_content);
		dto.setBoard_pwd(bbs_pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		int check = dao.updateBbs(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시물 수정 성공')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("bbs_content.do?no="+bbs_no);
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}

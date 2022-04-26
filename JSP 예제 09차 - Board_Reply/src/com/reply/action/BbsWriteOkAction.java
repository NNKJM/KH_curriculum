package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_content = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);

		BbsDAO dao = BbsDAO.getInstance();
		int check = dao.insertBoard(dto);
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시물 추가 성공')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else {
			out.println("<script>");
			out.println("alert('게시물 작성 실패')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("view/bbs_write.jsp");
		}
		return forward;
	}

}

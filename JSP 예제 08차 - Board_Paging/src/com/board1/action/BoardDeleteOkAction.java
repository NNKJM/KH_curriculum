package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String board_pwd = request.getParameter("board_pwd");
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.deleteBoard(board_no, board_pwd);
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			out.println("<script>");
			out.println("alert('글 삭제 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else if (check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}

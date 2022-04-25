package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardHit(board_no);
		
		BoardDTO content = dao.boardContent(board_no);
		
		request.setAttribute("bCon", content);
		request.setAttribute("page", nowPage);
	}

}

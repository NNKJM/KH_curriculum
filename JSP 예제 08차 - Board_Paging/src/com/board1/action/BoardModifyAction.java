package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardModifyAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = dao.boardContent(board_no);
		
		request.setAttribute("bMod", dto);
		request.setAttribute("page", nowPage);
	}

}

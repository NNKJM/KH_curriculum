package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardModifyAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = dao.boardContent(board_no);
		
		request.setAttribute("bMod", dto);
	}

}

package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardList = dao.getBoardList();
		
		request.setAttribute("bList", boardList);
	}

}

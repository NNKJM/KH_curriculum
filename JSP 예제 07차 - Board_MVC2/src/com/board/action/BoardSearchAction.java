package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String find_field = request.getParameter("find_field").trim();
		String find_name = request.getParameter("find_name").trim();
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> searchList = dao.searchBoard(find_field, find_name);
		System.out.println();
		
		int i = 1;
		for(BoardDTO data: searchList) {
			System.out.println(i + "번째 data : " + data);
			i++;
		}
		
		request.setAttribute("bSearchList", searchList);
		
	}

}

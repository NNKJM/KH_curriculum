package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.boardHit(board_no); // 조회수 증가
		
		dao.boardContent(board_no);
		BoardDTO content = dao.boardContent(board_no);
		request.setAttribute("Cont", content);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/board_content.jsp");
		rd.forward(request, response);
	}

}

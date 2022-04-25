package com.board1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.action.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length() + 1);
		
		String viewpage = null;
		Action action = null;
		
		if(command.equals("select.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewpage = "view/board_list.jsp";
		} else if (command.contentEquals("insert.do")) {
			viewpage = "view/board_write.jsp";
		} else if (command.contentEquals("insert_ok.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
		} else if (command.contentEquals("content.do")) {
			action = new BoardContentAction();
			action.execute(request, response);
			viewpage = "view/board_content.jsp";
		} else if (command.contentEquals("update.do")) {
			action = new BoardModifyAction();
			action.execute(request, response);
			viewpage = "view/board_update.jsp";
		} else if (command.contentEquals("update_ok.do")) {
			action = new BoardModifyOkAction();
			action.execute(request, response);
		} else if (command.contentEquals("delete.do")) {
			action = new BoardDeleteAction();
			action.execute(request, response);
			viewpage = "view/board_delete.jsp";
		} else if (command.contentEquals("delete_ok.do")) {
			action = new BoardDeleteOkAction();
			action.execute(request, response);
		} else if (command.contentEquals("search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewpage = "view/board_searchList.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewpage);
		rd.forward(request, response);
		
	}

}

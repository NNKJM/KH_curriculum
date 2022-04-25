package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : '/프로젝트명/파일명(*.do)' 라는 문자열을 반환해 주는 메서드
		String uri = request.getRequestURI();
		// System.out.println("URI : " + uri);
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환
		String path = request.getContextPath();
		// System.out.println("Path : " + path);
		
		String command = uri.substring(path.length()+1);
		// System.out.println("Command : " + command);
		
		Action action = null;
		if(command.contentEquals("select.do")) {
			action = new MemberListAction();
		} else if(command.contentEquals("insert.do")) {
			action = new MemberJoinAction();
		} else if(command.contentEquals("insert_ok.do")) {
			action = new MemberJoinOkAction();
		} else if(command.contentEquals("content.do")) {
			action = new MemberContentAction();
		} else if(command.contentEquals("update.do")) {
			action = new MemberUpdateAction();
		} else if(command.contentEquals("update_ok.do")) {
			action = new MemberUpdateOkAction();
		} else if(command.contentEquals("delete.do")) {
			action = new MemberDeleteAction();
		} else if(command.contentEquals("delete_ok.do")) {
			action = new MemberDeleteOkAction();
		}
		String path1 = action.execute(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		
		rd.forward(request, response);
	}
}

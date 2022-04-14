package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		MemberDAO dao = new MemberDAO();
		MemberDTO cont = dao.getContentMember(member_no);
		
		request.setAttribute("content", cont);
		RequestDispatcher rd = request.getRequestDispatcher("view/member_content.jsp");
		rd.forward(request, response);
	}

}

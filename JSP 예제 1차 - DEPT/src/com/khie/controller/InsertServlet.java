package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/insert_ok")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계
		int deptno = Integer.parseInt(request.getParameter("deptno").trim());
		String deptName = request.getParameter("deptName").trim();
		String location = request.getParameter("location").trim();
		
		// 2단계
		DeptDTO dto = new DeptDTO();
		dto.setDeptno(deptno);
		dto.setDname(deptName);
		dto.setLoc(location);
		
		// 3단계
		DeptDAO dao = new DeptDAO();
		int res = dao.insertDept(dto);
		PrintWriter out = response.getWriter();
		if(res > 0) {
			out.println("<script>");
			out.println("alert('부서 추가 성공')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('부서 추가 실패')");
			out.println("history.back");
			out.println("</script>");
			
		}
	}

}

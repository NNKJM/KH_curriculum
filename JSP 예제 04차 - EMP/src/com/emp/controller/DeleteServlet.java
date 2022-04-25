package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int emp_no = Integer.parseInt(request.getParameter("no").trim());
		EmpDAO dao = EmpDAO.getInstance();
		int check = dao.deleteEmp(emp_no);
	
	PrintWriter out = response.getWriter();
	
	if(check > 0) {
		out.println("<script>");
		out.println("alert('DELETE SUCCESS')");
		out.println("location.href='select.do'");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('DELETE FAIL')");
		out.println("history.go(-1)");
		out.println("</script>");
	}
}

}

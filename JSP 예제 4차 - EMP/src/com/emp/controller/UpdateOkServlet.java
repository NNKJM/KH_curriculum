package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		int emp_no = Integer.parseInt(request.getParameter("emp_no").trim());
		
		String emp_job = request.getParameter("emp_job").trim();
		int emp_mgr = Integer.parseInt(request.getParameter("emp_mgr").trim());
		int emp_comm = Integer.parseInt(request.getParameter("emp_comm").trim());
		int dept_no = Integer.parseInt(request.getParameter("dept_no").trim());

		EmpDTO dto = new EmpDTO();
		dto.setEmpno(emp_no);
		
		dto.setJob(emp_job);
		dto.setMgr(emp_mgr);
		dto.setComm(emp_comm);
		dto.setDeptno(dept_no);
		
		EmpDAO dao = EmpDAO.getInstance();
		int check = dao.updateEmp(dto);
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('제품 수정 성공')");
			out.println("location.href='content.do?no="+dto.getEmpno()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('제품 수정 실패')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
	}
}

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

@WebServlet("/insert_ok.do")
public class InsertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int Emp_empno = Integer.parseInt(request.getParameter("emp_empno").trim());
		System.out.println("INSERT OK EMPNO : " + Emp_empno);
		String Emp_ename = request.getParameter("emp_ename").trim();
		System.out.println("INSERT OK EMPNAME : " + Emp_ename);
		String Emp_job = request.getParameter("emp_job").trim();
		int Emp_mgr = Integer.parseInt(request.getParameter("emp_mgr").trim());
		int Emp_sal = Integer.parseInt(request.getParameter("emp_sal").trim());
		int Emp_comm = Integer.parseInt(request.getParameter("emp_comm").trim());
		int Emp_deptno = Integer.parseInt(request.getParameter("emp_deptno").trim());
		
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(Emp_empno);
		dto.setEname(Emp_ename);
		dto.setJob(Emp_job);
		dto.setMgr(Emp_mgr);
		dto.setSal(Emp_sal);
		dto.setComm(Emp_comm);
		dto.setDeptno(Emp_deptno);
		
		EmpDAO dao = EmpDAO.getInstance();
		int res = dao.insertEmp(dto);
		
		PrintWriter out = response.getWriter();
		System.out.println("res : " + res);
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('REGISTERING SUCCESS')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('FAIL')");
			out.println("history.back");
			out.println("</script>");
		}
	}

}


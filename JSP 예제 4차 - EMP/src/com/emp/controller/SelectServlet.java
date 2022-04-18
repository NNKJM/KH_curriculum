package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = EmpDAO.getInstance();
		List<EmpDTO> empList = dao.getEmpList();
		
		request.setAttribute("eList", empList);
		RequestDispatcher rd = request.getRequestDispatcher("view/emp_list.jsp");
		rd.forward(request, response);
	}

}

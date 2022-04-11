package com.khie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// index.jsp 페이지에서 요청 ==> 전체 부서 목록
		
		// 1단계 : DB와 연결 작업 진행
		// 준비과정 : DAO(Data Access Object) 객체 만들어야 함 & DTO(Data Transfer Object) 객체를 만들어야 함
		DeptDAO dao = new DeptDAO();
		
		// 2단계 : DB에서 DEPT 테이블의 전체 목록을 조회
		List<DeptDTO> deptList = dao.selectList();
		
		// 3단계 : 패이지 이동 작업 진행 시 데이터를 같이 넘겨주어야 함
		request.setAttribute("List", deptList);
		
		// 4단계 : 실제로 페이지 이동을 진행
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
		
		rd.forward(request,response);
	}

}

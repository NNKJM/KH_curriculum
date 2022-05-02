package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDAO;
import com.shop.model.AdminDTO;

public class AdminLoginOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String admin_id = request.getParameter("admin_id").trim();
		String admin_pwd = request.getParameter("admin_pwd").trim();
		
		AdminDAO dao = AdminDAO.getInstance();
		int check = dao.adminCheck(admin_id, admin_pwd);
		
		HttpSession session = request.getSession();
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			AdminDTO dto = dao.getAdmin(admin_id);
			session.setAttribute("adminId", dto.getAdmin_id());
			session.setAttribute("adminName", dto.getAdmin_name());
			
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
		} else if (check == -1) {
			out.println("<script>");
			out.println("alert('관리자 비밀번호 오류')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('존재하지 않는 관리자')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}

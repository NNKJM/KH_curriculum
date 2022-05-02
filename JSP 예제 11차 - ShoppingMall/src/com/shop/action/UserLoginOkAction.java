package com.shop.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.UserDAO;
import com.shop.model.UserDTO;

public class UserLoginOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user_id = request.getParameter("user_id").trim();
		String user_pwd = request.getParameter("user_pwd").trim();
		
		UserDAO dao = UserDAO.getInstance();
		int check = dao.userCheck(user_id, user_pwd);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if(check > 0) {
			UserDTO dto = dao.getMember(user_id);
			session.setAttribute("userId", dto.getMemid());
			session.setAttribute("userName", dto.getMemname());
			forward.setRedirect(true);
			forward.setPath("user_main.do");
		} else if (check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호 오류')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('존재하지 않는 유저')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}

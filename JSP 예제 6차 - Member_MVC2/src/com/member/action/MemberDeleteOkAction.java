package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class MemberDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_no = Integer.parseInt(request.getParameter("mem_no").trim());

		MemberDAO dao = MemberDAO.getInstance();
		int check = dao.deleteMember(member_no, member_pwd);
		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('회원 삭제 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else if (check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}

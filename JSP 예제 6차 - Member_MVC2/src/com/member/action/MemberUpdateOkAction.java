package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String member_id = request.getParameter("mem_id").trim();
		String member_name = request.getParameter("mem_name").trim();
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_age = Integer.parseInt(request.getParameter("mem_age").trim());
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage"));
		String member_job = request.getParameter("mem_job").trim();
		String member_addr = request.getParameter("mem_addr").trim();
		
		int member_num = Integer.parseInt(request.getParameter("num").trim());
		String member_dbpwd = request.getParameter("db_pwd").trim();
		
		MemberDTO dto = new MemberDTO();
		dto.setNum(member_num);
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		
		MemberDAO dao = MemberDAO.getInstance();
		

		PrintWriter out = response.getWriter();
		if(dto.getPwd().equals(member_dbpwd)) {
			int res = dao.updateMember(dto);
			if(res > 0) {
				out.println("<script>");
				out.println("alert('회원정보 수정성공')");
				out.println("location.href='content.do?num="+dto.getNum()+"'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원정보 수정실패')");
				out.println("history.back()");
				out.println("</script>");
			} 
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}

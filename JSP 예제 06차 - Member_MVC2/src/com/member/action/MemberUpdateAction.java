package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getContentMember(member_no);
		
		request.setAttribute("Modify", dto);
		return "view/member_update.jsp";
	}

}

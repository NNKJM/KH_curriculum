package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		
		request.setAttribute("No", member_no);
		return "view/member_delete.jsp";	
	}

}

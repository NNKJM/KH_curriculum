package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberListAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = MemberDAO.getInstance();
		List<MemberDTO> memberList = dao.getMemberList();
		
		request.setAttribute("List", memberList);
		return "view/member_list.jsp";
	}

}

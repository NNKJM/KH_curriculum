package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "view/member_join.jsp";
	}

}

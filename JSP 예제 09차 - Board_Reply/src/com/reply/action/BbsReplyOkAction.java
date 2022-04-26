package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String reply_writer = request.getParameter("reply_writer").trim();
		String reply_title = request.getParameter("reply_title").trim();
		String reply_content = request.getParameter("reply_content").trim();
		String reply_pwd = request.getParameter("reply_pwd").trim();
		
		int bbs_no = Integer.parseInt(request.getParameter("bbs_no").trim());
		int bbs_group = Integer.parseInt(request.getParameter("bbs_group").trim());
		int bbs_step = Integer.parseInt(request.getParameter("bbs_step").trim());
		int bbs_indent = Integer.parseInt(request.getParameter("bbs_indent").trim());
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_writer(reply_writer);
		dto.setBoard_title(reply_title);
		dto.setBoard_cont(reply_content);
		dto.setBoard_pwd(reply_pwd);
		
		dto.setBoard_no(bbs_no);
		dto.setBoard_group(bbs_group);
		dto.setBoard_step(bbs_step);
		dto.setBoard_indent(bbs_indent);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		dao.replyUpdate(bbs_group, bbs_step);
		int check = dao.replyBbs(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else {
			out.println("<script>");
			out.println("alert('댓글 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}

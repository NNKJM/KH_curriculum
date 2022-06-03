package com.khie.jdbc01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khie.model.MemberDAO;
import com.khie.model.MemberDTO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("member_list.do")
	public String list(Model model) {
		List<MemberDTO> list = this.dao.getMemberList();
		model.addAttribute("memList", list);
		return "member_list";
	} // list END
	
	@RequestMapping("member_insert.do")
	public String insert() {
		return "member_insert";
	} // insert END

	@RequestMapping("member_insert_ok.do")
	public void insert_ok(MemberDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.insertMember(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원등록성공')");
			out.println("location.href='member_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // insertOk END
	
	@RequestMapping("member_content.do")
	public String content(@RequestParam("num") int num, Model model) {
		MemberDTO dto = this.dao.getMember(num);
		model.addAttribute("Cont", dto);
		return "member_content";
	} // content END
	
	@RequestMapping("member_modify.do")
	public String modify(@RequestParam("num") int num, Model model) {
		MemberDTO dto = this.dao.getMember(num);
		model.addAttribute("Modify", dto);
		return "member_modify";
	} // modify END

	@RequestMapping("member_modify_ok.do")
	public void modifyOk(MemberDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.updateMember(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원수정성공')");
			out.println("location.href='member_content.do?num="+dto.getNum()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // modifyOk END

	@RequestMapping("member_delete.do")
	public void deleteMember(@RequestParam("num") int num, HttpServletResponse response) throws IOException {
		int check = this.dao.deleteMember(num);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			this.dao.updateSequence(num);
			out.println("<script>");
			out.println("alert('회원삭제성공')");
			out.println("location.href='member_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // delete END
	

	@RequestMapping("member_search.do")
	public String search(@RequestParam("field") String field, @RequestParam("keyword") String keyword, Model model) throws IOException {
		List<MemberDTO> list = this.dao.searchMemberList(field, keyword);
		model.addAttribute("searchList", list);
		return "member_searchList";
	} // searchMember END
}

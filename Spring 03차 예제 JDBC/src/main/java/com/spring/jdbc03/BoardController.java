package com.spring.jdbc03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.BoardDAO;
import com.spring.model.BoardDTO;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("board_list.do")
	public String list(Model model) {
		List<BoardDTO> list = this.dao.getBoardList();
		model.addAttribute("List", list);
		return "board_list";
	} // board_list
	
	@RequestMapping("board_insert.do")
	public String insert() {
		return "board_insert";
	} // board_insert

	@RequestMapping("board_insert_ok.do")
	public void insertOk(BoardDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.writeBoard(dto);response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('글 등록 성공')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // board_insert_ok
	
	@RequestMapping("board_content.do")
	public String content(@RequestParam("num") int num, Model model) {
		this.dao.hitBoard(num);
		BoardDTO dto = this.dao.readBoard(num);
		model.addAttribute("Cont", dto);
		return "board_content";
	} // board_content
	
	@RequestMapping("board_update.do")
	public String update(@RequestParam("num") int num, Model model) {
		BoardDTO dto = this.dao.readBoard(num);
		model.addAttribute("Modify", dto);
		return "board_update";
	} // board_update
	
	@RequestMapping("board_update_ok.do")
	public void update_ok(BoardDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.updateBoard(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('글 수정 성공')");
			out.println("location.href='board_content.do?num="+dto.getBoard_no()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // board_update_ok
	
	@RequestMapping("board_delete.do")
	public void delete(@RequestParam("num") int num, HttpServletResponse response) throws IOException {
		int check = this.dao.deleteBoard(num);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			this.dao.updateBoard_no(num);
			out.println("<script>");
			out.println("alert('글 삭제 성공')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // delete
	
	@RequestMapping("board_search.do")
	public String search(@RequestParam("field") String field, @RequestParam("keyword") String keyword, Model model) throws IOException {
		List<BoardDTO> list = this.dao.searchBoardList(field, keyword);
		model.addAttribute("searchList", list);
		return "board_searchList";
	} // search

}

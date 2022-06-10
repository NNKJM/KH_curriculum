package com.spring.mybatis02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.DeptDTO;
import com.spring.model.EmpDAO;
import com.spring.model.EmpDTO;

@Controller
public class EmpController {
	
	@Autowired
	private EmpDAO dao;
	
	@RequestMapping("emp_list.do")
	public String list(Model model) {
		List<EmpDTO> list = this.dao.getEmpList();
		model.addAttribute("List", list);
		return "emp_list";
	}
	
	@RequestMapping("emp_insert.do")
	public String insert(Model model) {
		List<EmpDTO> elist = this.dao.getEmpList();
		List<DeptDTO> dlist = this.dao.deptList();
		List<String> jlist = this.dao.getJobList();
		model.addAttribute("eList", elist);
		model.addAttribute("jList", jlist);
		model.addAttribute("dList", dlist);
		return "emp_insert";
	}

	@RequestMapping("emp_insert_ok.do")
	public void insertOk(EmpDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.insertEmp(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('사원 등록 성공')");
			out.println("location.href='emp_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('사원 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("emp_content.do")
	public String content(@RequestParam("num") int num, Model model) {
		EmpDTO dto = this.dao.getEmp(num);
		String mlist = this.dao.getMgr(num);
		model.addAttribute("Cont", dto);
		model.addAttribute("mList", mlist);
		return "emp_content";
	}

	@RequestMapping("emp_modify.do")
	public String modify(@RequestParam("num") int num, Model model) {
		EmpDTO dto = this.dao.getEmp(num);
		List<EmpDTO> elist = this.dao.getEmpList();
		List<DeptDTO> dlist = this.dao.deptList();
		List<String> jlist = this.dao.getJobList();
		model.addAttribute("eList", elist);
		model.addAttribute("jList", jlist);
		model.addAttribute("dList", dlist);
		model.addAttribute("modify", dto);
		return "emp_modify";
	}
	
	@RequestMapping("emp_modify_ok.do")
	public void modifyOk(EmpDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.updateEmp(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('사원 수정 성공')");
			out.println("location.href='emp_content.do?num="+dto.getEmpno()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('사원 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	@RequestMapping("emp_delete.do")
	public void delete(@RequestParam("num") int num, HttpServletResponse response) throws IOException {
		int check = this.dao.deleteEmp(num);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
			out.println("<script>");
			out.println("alert('사원 삭제 성공')");
			out.println("location.href='emp_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('사원 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}

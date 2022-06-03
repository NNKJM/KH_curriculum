package com.spring.jdbc02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.CategoryDTO;
import com.spring.model.ProductDAO;
import com.spring.model.ProductDTO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductDAO dao;

	@RequestMapping("product_list.do")
	public String list(Model model) {
		List<ProductDTO> list = this.dao.getProductList();
		model.addAttribute("pList", list);
		return "product_list";
	} // list END
	
	@RequestMapping("product_insert.do")
	public String insert(Model model) {
		List<CategoryDTO> list = this.dao.categoryList();
		
		model.addAttribute("cartList", list);
		
		return "product_insert";
	}
	
	@RequestMapping("product_insert_ok.do")
	public void insertOk(ProductDTO dto, HttpServletResponse response) throws IOException {
		
		int check = this.dao.insertProduct(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('제품 등록 성공')");
			out.println("location.href='product_list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('제품 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("product_content.do")
	public String content(@RequestParam("num") int num, Model model) {
		ProductDTO content = this.dao.getProduct(num);
		model.addAttribute("Cont", content);
		return "product_content";
	} // list END
	
	@RequestMapping("product_update.do")
	public String update(@RequestParam("num") int num, Model model) {
		ProductDTO dto = this.dao.getProduct(num);
		model.addAttribute("Modify", dto);
		return "product_update";
	} // modify END

	@RequestMapping("product_update_ok.do")
	public void updateOk(ProductDTO dto, HttpServletResponse response) throws IOException {
		int check = this.dao.updateProduct(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('제품수정성공')");
			out.println("location.href='product_content.do?num='"+dto.getPnum()+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('제품수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // modifyOk END
	
	@RequestMapping("product_delete.do")
	public void deleteproduct(@RequestParam("num") int num, HttpServletResponse response) throws IOException {
		int check = this.dao.deleteProduct(num);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('제품삭제성공')");
			out.println("location.href='product_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('제품삭제실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	} // delete END
	
	@RequestMapping("product_search.do")
	public String search(@RequestParam("field") String field, @RequestParam("keyword") String keyword, Model model) throws IOException {
		List<ProductDTO> list = this.dao.searchProductList(field, keyword);
		model.addAttribute("searchList", list);
		return "product_searchList";
	} // search END
}

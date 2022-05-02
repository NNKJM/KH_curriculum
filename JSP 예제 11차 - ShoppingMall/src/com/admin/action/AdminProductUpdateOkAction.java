package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductUpdateOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String saveFolder="C:\\NCS\\workspace(jsp)\\18_ShoppingMall\\WebContent\\upload";
		int fileSize = 10 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String p_category = multi.getParameter("p_category").trim();
		String p_name = multi.getParameter("p_name").trim();
		String p_company = multi.getParameter("p_company").trim();
		int p_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int p_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String p_spec = multi.getParameter("p_spec").trim();
		String p_contents = multi.getParameter("p_contents").trim();
		int p_point = Integer.parseInt(multi.getParameter("p_point").trim());
		
		int p_num = Integer.parseInt(multi.getParameter("p_num").trim());
		String p_image;
		if(multi.getFilesystemName("p_image_new") == null) {
			p_image = multi.getParameter("p_image_old");
		} else {
			p_image = multi.getParameter("p_image_new");
		}
		
		ProductDTO dto = new ProductDTO();
		dto.setPimage(p_image);
		dto.setPqty(p_qty);
		dto.setPrice(p_price);
		dto.setPspec(p_spec);
		dto.setPcontents(p_contents);
		dto.setPoint(p_point);
		dto.setPnum(p_num);
		ProductDAO dao = ProductDAO.getInstance();
		int check = dao.updateProduct(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do");
		} else {
			out.println("<script>");
			out.println("alert('상품 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}

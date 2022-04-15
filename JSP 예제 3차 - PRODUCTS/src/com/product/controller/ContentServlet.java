package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int product_no = Integer.parseInt(request.getParameter("no").trim());
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO cont = dao.getContentProduct(product_no);
		
		request.setAttribute("content", cont);
		RequestDispatcher rd = request.getRequestDispatcher("view/product_content.jsp");
		rd.forward(request, response);
	}

}

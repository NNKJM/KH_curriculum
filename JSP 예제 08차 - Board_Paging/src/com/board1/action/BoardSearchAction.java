package com.board1.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String search_field = request.getParameter("search_field").trim();
		String search_keyword = request.getParameter("search_keyword").trim();
		
		
		int rowsize = 3; // 페이지 당 보여질 게시물
		int block = 3; // 아래에 보여질 페이지의 최대수
		int totalRecord = 0; // DB 상의 게시물의 전체 수
		int allPage = 0; // 전체 페이지 수
		
		int page = 0; // 현재 페이지 변수
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		} else { // 처음으로 "전체 게시물 목록" 태그 선택 시
			page = 1;
		}
		
		int startNo = (page * rowsize) - (rowsize - 1); // 해당 페이지에서 시작 번호
		int endNo = (page * rowsize); // 해당 페이지에서 끝 번호
		int startBlock = (((page - 1) / block) * block) + 1; // 해당 페이지에서 시작 블럭
		int endBlock = (((page - 1) / block) * block) + block; // 해당 페이지에서 끝  블럭
		
		BoardDAO dao = BoardDAO.getInstance();
		
		totalRecord = dao.searchListCount(search_field, search_keyword); // DB 상 전체 게시물
		
		allPage = (int)Math.ceil(totalRecord / (double)rowsize);
		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나눔
		// 이 과정을 거치면 전체 페이지 수가 나옴
		// 전체 페이지 수가 나올 떄 나머지가 있으면 무조건 페이지 수를 하나 올려 주어야 함
		
		if(endBlock > allPage) {
			endBlock = allPage;
		}
		List<BoardDTO> searchList = dao.searchBoardList(search_field, search_keyword, page, rowsize);
		
		// 모든 값을 view로 이동
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("search_field", search_field);
		request.setAttribute("search_keyword", search_keyword);
		request.setAttribute("sList", searchList);
		
	}

}

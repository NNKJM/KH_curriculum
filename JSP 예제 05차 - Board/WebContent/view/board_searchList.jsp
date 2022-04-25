<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDTO> search = (List<BoardDTO>)request.getAttribute("Search");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<hr width="30%" color="red">
		<h3>BOARD 테이블 검색 게시물 목록</h3>
	<hr width="30%" color="red">
		<table border="1" cellspacing="0" width="700">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<% if (search.size() != 0) {
				for (int i = 0; i < search.size(); i++) {
					BoardDTO dto = search.get(i); %>
					<tr>
						<td><%=dto.getBoard_no() %></td>
						<td><a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getBoard_no() %> ">
							<%=dto.getBoard_title() %></a> </td>
						<td><%=dto.getBoard_writer() %></td>
						<td><%=dto.getBoard_date().substring(0,10) %></td>
						<td><%=dto.getBoard_hit() %></td>
					</tr>
				<% }
				} else { %>
					<tr>
						<td colspan="5" align="center">
							<h3>검색된 게시글이 없습니다</h3>
					</tr>
					<% } %>
					<tr>
				<td colspan="5" align="right"><input type="button" value="전체 게시물"
					onclick="location.href='select.do'"></td>
				</tr>
		</table>
</div>
</body>
</html>
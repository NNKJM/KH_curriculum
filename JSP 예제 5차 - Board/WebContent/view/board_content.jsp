<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDTO cont = (BoardDTO) request.getAttribute("Cont");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="tomato">
			<h3>BOARD 테이블 게시글 읽기</h3>
		<hr width="30%" color="tomato">
		<br>
		<table border="1" cellspacing="0" width="400">
			<%
				if(cont == null){
			%>	<tr><th colspan="2"><h3>조회된 게시글이 없습니다</h3></th></tr>
			<%	} else { %>
		<tr>
			<th>작성자</th>
			<td><%=cont.getBoard_writer() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=cont.getBoard_title() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="7" cols="20" readonly><%=cont.getBoard_cont() %></textarea></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=cont.getBoard_hit() %></td>
		</tr>
		<tr>
			<%
				if(cont.getBoard_update()==null){
			%>
			<th>작성일자</th>
			<td><%=cont.getBoard_date() %></td>
			<% } else { %>
			<th>수정일자</th>
			<td><%=cont.getBoard_update() %></td>
			<% } %>
		</tr>
		<% } %>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글 수정" onclick="location.href='update.do?no=<%=cont.getBoard_no() %>'">
				<input type="button" value="글 삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){
						location.href='view/board_delete.jsp?no=<%=cont.getBoard_no()%>'
					}else{return;}">
				<input type="button" value="전체 목록" onclick="location.href='select.do'">
			</td>
		</tr>
		</table>
		</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="violet">
			<h3>BOARD 테이블 검색 게시물 목록</h3>
		<hr width="30%" color="violet">
		<br>
		<table border="1" cellspacing="0" width="700">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:set var="searchlist" value="${bSearchList }" />
			<c:if test="${!empty searchlist }">
				<c:forEach items="${searchlist }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="<%=request.getContextPath() %>/content.do?no=${dto.getBoard_no() } ">${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
						<td>${dto.getBoard_hit() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty searchlist }">
				<tr>
					<td colspan="5"><h3 align="center">검색에 해당하는 글이 없습니다.</h3></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="right"><input type="button" value="전체 게시물" onclick="location.href='select.do'"></td>
			</tr>
		</table>
	</div>
</body>
</html>
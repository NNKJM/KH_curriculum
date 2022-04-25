<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="orange">
			<h3>BOARD 테이블 전체 게시물 목록</h3>
		<hr width="30%" color="orange">
		<table border="1" cellspacing="0" width="700">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:set var="list" value="${bList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td><a href="<%=request.getContextPath() %>/content.do?no=${dto.getBoard_no() } ">${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
						<td>${dto.getBoard_hit() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5">아직 쓰여진 글이 없습니다.</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="right">
				<input type="button" value="글쓰기" onclick="location.href='insert.do'"></td>
			</tr>
		</table>
		<br>
		<form method = "post" action = "<%=request.getContextPath()%>/search.do">
			<select name="find_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			<input type="text" name="find_name">
			<input type="submit" value="검색">
		</form>
	</div>
</body>
</html>
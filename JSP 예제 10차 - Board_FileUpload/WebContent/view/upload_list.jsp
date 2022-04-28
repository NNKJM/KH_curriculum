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
		<hr width="30%" color="red">
			<h3>UPLOAD 테이블 게시판 전체 리스트 페이지</h3>
		<hr width="30%" color="red">
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getUpload_no() }</td>
						<td><a href="<%=request.getContextPath() %>/upload_content.do?no=${dto.getUpload_no() } ">${dto.getUpload_title() }</a></td>
						<td>${dto.getUpload_writer() }</td>
						<td>${dto.getUpload_date().substring(0,10) }</td>
						<td>${dto.getUpload_hit() }</td>
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
				<input type="button" value="글쓰기" onclick="location.href='upload_write.do'"></td>
			</tr>
		</table>
	</div>
</body>
</html>
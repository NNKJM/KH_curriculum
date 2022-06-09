<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<div align="center">
		<hr width="35%" color="yellow">
			<h3>게시판 전체 목록</h3>
		<hr width="35%" color="yellow">
		<br>
		<table border="1" cellspacing="0" width="750">
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>글쓴이</th>
				<th>조회수</th>
				<th>작성날짜</th>
			</tr>
			<c:set var="list" value="${searchList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td> <a href="<%=request.getContextPath() %>/board_content.do?num=${dto.getBoard_no() }">${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td colspan="5" align="center">
							<h3>검색된 글이 없습니다</h3>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="5" align="right">
					<input type="submit" value="글쓰기" onclick="location.href='board_insert.do'">&nbsp;&nbsp;&nbsp;
					<input type="submit" value="전체목록" onclick="location.href='board_list.do'">
				</td>
			</tr>
		</table>
		<form method="post" action="<%=request.getContextPath() %>/board_searchList.do">
			<select name="field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">글쓴이</option>
			</select>
			<input type="text" name="keyword">&nbsp;&nbsp;
			<input type="submit" value="검색">
		</form>
	</div>
</body>
</html>
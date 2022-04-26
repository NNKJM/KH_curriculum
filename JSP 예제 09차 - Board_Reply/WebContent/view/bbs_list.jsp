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
		<hr width="30%" color="marmoon">
			<h3>JSP_BBS 테이블 게시판 전체 리스트 페이지</h3>
		<hr width="30%" color="marmoon">
		<table border="1" cellspacing="0" width="650">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>그룹</th>
				<th>스텝</th>
				<th>인덴트</th>
			</tr>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td>
						<c:if test="${dto.getBoard_indent() != 0 }">
							<c:forEach begin="1" end="${dto.getBoard_indent() }">
								☞
							</c:forEach>
						</c:if>
						<a href="<%=request.getContextPath() %>/bbs_content.do?no=${dto.getBoard_no() } ">${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_writer() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_group() }</td>
						<td>${dto.getBoard_step() }</td>
						<td>${dto.getBoard_indent() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="8">아직 쓰여진 글이 없습니다.</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="8" align="right">
				<input type="button" value="글쓰기" onclick="location.href='bbs_write.do'"></td>
			</tr>
		</table>
	</div>
</body>
</html>
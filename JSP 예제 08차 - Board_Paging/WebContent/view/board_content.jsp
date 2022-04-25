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
		<hr width="30%" color="green">
			<h3>BOARD 테이블 읽기</h3>
		<hr width="30%" color="green">
		<br>
		<table border="1" cellspacing="0" width="400">
			<c:set var="dto" value="${bCon }" />
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${dto.getBoard_title() }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="7" cols="20" readonly>${dto.getBoard_cont() }</textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				<c:if test="${empty dto.getBoard_update() }">
					<tr>
						<th>작성일자</th>
						<td>${dto.getBoard_date().substring(0,16) }</td>
					</tr>
				</c:if>
				<c:if test="${!empty dto.getBoard_update() }">
					<tr>
						<th>작성일자</th>
						<td>${dto.getBoard_date().substring(0,16) }</td>
					</tr>
					<tr>
						<th>수정일자</th>
						<td>${dto.getBoard_update().substring(0,16) }</td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${empty dto }">
				<tr><th colspan="2"><h3>조회된 게시글이 없습니다</h3></th></tr>
			</c:if>
			<tr>
			<td colspan="2" align="center">
				<input type="button" value="글 수정" onclick="location.href='update.do?no=${dto.getBoard_no() }&page=${page }'">
				<input type="button" value="글 삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')){
															location.href='delete.do?no=${dto.getBoard_no()}&page=${page }'
															}else{return;}">
				<input type="button" value="전체 목록" onclick="location.href='select.do?page=${page }'">
			</td>
		</tr>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${upCont }" />
		<hr width="30%" color="marmoon">
		<h3>${dto.getUpload_writer() }님의 게시글입니다.</h3>
		<hr width="30%" color="marmoon">
		<br>
		<table border="1" cellspacing="0" width="400">
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getUpload_writer() }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${dto.getUpload_title() }</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="7" cols="20" readonly>${dto.getUpload_cont() }</textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><c:if test="${!empty dto.getUpload_pwd() }">
							<c:forEach begin="1" end="${dto.getUpload_pwd().length() }">
								*
							</c:forEach>
						</c:if></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><a href="<%=request.getContextPath() %>/upload${dto.getUpload_file() }" target="_blank">${dto.getUpload_file() }</a></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getUpload_hit() }</td>
				</tr>
				<c:if test="${empty dto.getUpload_update() }">
					<tr>
						<th>작성일자</th>
						<td>${dto.getUpload_date().substring(0,16) }</td>
					</tr>
				</c:if>
				<c:if test="${!empty dto.getUpload_update() }">
					<tr>
						<th>작성일자</th>
						<td>${dto.getUpload_date().substring(0,16) }</td>
					</tr>
					<tr>
						<th>수정일자</th>
						<td>${dto.getUpload_update().substring(0,16) }</td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<th colspan="2"><h3>조회된 게시글이 없습니다</h3></th>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center"><input type="button"
					value="글 수정"
					onclick="location.href='upload_update.do?no=${dto.getUpload_no() }'">
					<input type="button" value="글 삭제"
					onclick="if(confirm('정말로 삭제하시겠습니까?')){
																location.href='upload_delete.do?no=${dto.getUpload_no()}'
																}else{return;}">
					<input type="button" value="전체 목록"
					onclick="location.href='upload_list.do'"></td>
			</tr>
		</table>
	</div>
</body>
</html>
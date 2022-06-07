<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>글쓰기 페이지</h3>
		<hr width="50%" color="blue">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/board_insert_ok.do">
			<table border="1" cellspacing="0" width="450">
				<tr>
					<th>글쓴이</th>
					<td><input name="board_writer"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input name="board_title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="7" cols="20" name="board_cont"></textarea>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="board_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
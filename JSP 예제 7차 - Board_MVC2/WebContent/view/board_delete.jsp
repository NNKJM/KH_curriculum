<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="indigo">
			<h3>BOARD 테이블 게시글 삭제 폼 페이지</h3>
		<hr width="30%" color="indigo">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/delete_ok.do">
			<input type="hidden" name="board_no" value="${No }">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>삭제할 글의 비밀번호</th>
					<td><input type="password" name="board_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 삭제">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시 작성">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
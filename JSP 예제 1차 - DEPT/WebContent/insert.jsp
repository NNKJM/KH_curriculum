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
		<hr width="30%" color="gray">
		<h3>DEPT 테이블 부서 추가 화면</h3>

		<hr width="30%" color="gray">
		<br>
		<form method="post" action="insert_ok">
			<table border="1" cellspacing="0" width="250">
				<tr>
					<th>부서번호</th>
					<td><input type="text" name="deptno"></td>
				</tr>
				<tr>
					<th>부서명</th>
					<td><input type="text" name="deptName"></td>
				</tr>
				<tr>
					<th>부서위치</th>
					<td><input type="text" name="location"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="부서추가"> <input type="reset" value="다시작성"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
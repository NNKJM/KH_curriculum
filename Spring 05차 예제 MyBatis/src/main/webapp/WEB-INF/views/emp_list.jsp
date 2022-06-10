<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="#444444">
			<h3>EMP 테이블  전체 리스트</h3>
		<hr width="50%" color="#444444">
		<br>
		<table border="1" cellspacing="0" width="750">
			<tr>
				<th>사원 번호</th>
				<th>사원 이름</th>
				<th>직책</th>
				<th>고용일</th>
				<th>급여</th>
				<th>부서 번호</th>
				<th>부서 업무</th>
				<th>근무지</th>
			</tr>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getEmpno() }</td>
						<td> <a href="<%=request.getContextPath() %>/emp_content.do?num=${dto.getEmpno() }">${dto.getEname() }</a></td>
						<td> ${dto.getJob() }</td>
						<td> ${dto.getHiredate().substring(0,10) }</td>
						<td> ${dto.getSal() + dto.getComm()}원</td>
						<td> ${dto.getDeptno() }</td>
						<td> ${dto.getDname() }</td>
						<td> ${dto.getLoc() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td colspan="8" align="center">
							<h3>검색된 사원이 없습니다</h3></td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="8" align="center">
					<input type="submit" value="사원등록" onclick="location.href='emp_insert.do'">&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
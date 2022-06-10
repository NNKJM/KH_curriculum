<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contents</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${Cont }" />
		<hr width="30%" color="#888888">
			<h3>EMP 테이블 ${dto.getEname() } 회원 정보 상세 페이지</h3>
		<hr width="30%" color="#888888">
		<br>
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>사원No.</th>
				<td>${dto.getEmpno() }</td>
			</tr>
			<tr>
				<th>사원 이름</th>
				<td>${dto.getEname() }</td>
			</tr>
			<tr>
				<th>직책</th>
				<td>${dto.getJob() }</td>
			</tr>
			<c:set var="mgr" value="${mList }" />
			<tr>
				<th>매니저</th>
				<td>${mgr }[${dto.getMgr() }]</td>
			</tr>
			<tr>
				<th>급여</th>
				<td><fmt:formatNumber value="${dto.getSal() }" />원</td>
			</tr>
			<tr>
				<th>보너스</th>
				<td><fmt:formatNumber value="${dto.getComm() }" />원</td>
			</tr>
			<tr>
				<th>부서</th>
				<td>${dto.getDeptno() }</td>
			</tr>
			<tr>
				<th>근무지</th>
				<td>${dto.getLoc() }</td>
			</tr>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>회원에 대한 정보가 없습니다</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="사원수정" onclick="location.href='emp_modify.do?num=${dto.getEmpno() }'">
					<input type="button" value="사원삭제" onclick="if(confirm('정말로 삭제하시겠습니까')) { location.href='emp_delete.do?num=${dto.getEmpno() }' } else { return; }">
					<input type="button" value="목록으로" onclick="location.href='emp_list.do'">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>
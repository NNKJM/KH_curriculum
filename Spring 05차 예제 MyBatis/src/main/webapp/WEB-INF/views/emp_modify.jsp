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
		<c:set var="dto" value="${modify }" />
		<hr width="50%" color="marmoon">
			<h3>MEMBER10 테이블 회원 수정 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/member_modify_ok.do">
			<table border="1" cellspacing="0" width="450">
				<input type="hidden" name="num" value="${dto.getEmpno() }">
				<tr>
					<th>사원 번호</th>
					<td><input name="empno" value=${dto.getEmpno() } readonly></td>
				</tr>
				<tr>
					<th>사원 이름</th>
					<td><input name="ename" value=${dto.getEname() } readonly></td>
				</tr>
				<c:set var="jlist" value="${jList }" />
				<tr>
					<th>직책</th>
					<td>
		               <select name="job" value=${jlist }>
		                  <c:if test="${empty jlist }">
		                     <option value="">:::등록 직책 없음:::</option>
		                  </c:if>
		                  <c:if test="${!empty jlist }">
		                     <c:forEach items="${jlist }" var="str">
		                        <option value="${str }">${str }</option>
		                     </c:forEach>
		                  </c:if>
		               </select>
		            </td>
				</tr>
				<c:set var="elist" value="${eList }" />
				<tr>
					<th>매니저</th>
					<td>
		               <select name="mgr" value=${dto.getMgr() }>
		                  <c:if test="${empty elist }">
		                     <option value="">:::사원 없음:::</option>
		                  </c:if>
		                  <c:if test="${!empty elist }">
		                     <c:forEach items="${elist }" var="dto">
		                        <option value="${dto.getEmpno() }">${dto.getEname() } [${dto.getEmpno() }]</option>
		                     </c:forEach>
		                  </c:if>
		               </select>
		            </td>
				</tr>
				<tr>
					<th>급여</th>
					<td><input type="number" name="sal" value=${dto.getSal() }></td>
				</tr>
				<tr>
					<th>보너스</th>
					<td><input type="number" name="comm" value=${dto.getComm() }></td>
				</tr>			
				<c:set var="dlist" value="${dList }" />
				<tr>
					<th>부서번호</th>
					<td>
		               <select name="deptno" value=${dto.getDeptno() }>
		                  <c:if test="${empty dlist }">
		                     <option value="">:::부서 코드 없음:::</option>
		                  </c:if>
		                  <c:if test="${!empty dlist }">
		                     <c:forEach items="${dlist }" var="dto">
		                        <option value="${dto.getDeptno() }">${dto.getDname() } [${dto.getDeptno() }]</option>
		                     </c:forEach>
		                  </c:if>
		               </select>
		            </td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
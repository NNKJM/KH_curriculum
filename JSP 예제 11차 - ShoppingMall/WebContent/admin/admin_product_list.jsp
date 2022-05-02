<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	<div align="center">
		<hr width="65%" color="red">
			<h3>SHOP_PRODUCTS 테이블 제품 전체 리스트 페이지</h3>
		<hr width="65%" color="red">
		<br>
		<table border="1" cellspacing="0" width="65%">
			<tr bgcolor="#ffcc00">
				<th>제품번호</th> <th>카테고리 코드</th> <th>제품명</th>
				<th>이미지</th> <th>제품 가격</th> <th>수량</th>
				<th>제조사</th> <th>제품 등록일</th> <th>수정&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;삭제</th>
			</tr>
			<c:set var="list" value="${productList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getPnum() }</td>
						<td>${dto.getPcategory_fk() }</td>
						<td>${dto.getPname() }</td>
						<td><img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }"
										width="60" height="50"></td>
						<td><fmt:formatNumber value="${dto.getPrice() }" />원</td>
						<td>${dto.getPqty() }</td>
						<td>${dto.getPcompany() }</td>
						<td>${dto.getPinputdate().substring(0,10) }</td>
						<td>
							<a href="<%=request.getContextPath() %>/admin_product_update.do?pnum=${dto.getPnum() }">수정</a>
								&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
							<a href="<%=request.getContextPath() %>/admin_product_delete.do?pnum=${dto.getPnum() }">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="9" align="center"><h3>검색된 제품 리스트가 없습니다</h3></td>
				</tr>
			</c:if>
		</table>
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="35%" color="red">
			<h3>PRODUCTS 테이블 상품 전체 리스트</h3>
		<hr width="35%" color="red">
		<br>
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>상품 번호</th>
				<th>카테고리 번호</th>
				<th>제품명</th>
				<th>제조사</th>
			</tr>
			<c:set var="list" value="${pList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getPnum() }</td>
						<td> <a href="<%=request.getContextPath() %>/product_content.do?num=${dto.getPnum() }">${dto.getCategory_fk() }</a></td>
						<td>${dto.getProducts_name() }</td>
						<td>${dto.getCompany() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td colspan="4" align="center">
							<h3>검색된 상품이 없습니다</h3>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="4" align="center"><input type="submit"
					value="상품 등록" onclick="location.href='product_insert.do'">&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<form method="post" action="<%=request.getContextPath() %>/product_search.do">
			<select name="field">
				<option value="products_name">제품명</option>
				<option value="company">제조사</option>
			</select>
			<input type="text" name="keyword">&nbsp;&nbsp;
			<input type="submit" value="검색">
		</form>
	</div>
</body>
</html>
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
	<div align="center">
		<c:set var="dto" value="${Cont }" />
		<hr width="30%" color="red">
			<h3>Products 테이블 ${dto.getProducts_name() }  정보 상세 페이지</h3>
		<hr width="30%" color="red">
		<br>
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>상품No.</th>
				<td>${dto.getPnum() }</td>
			</tr>
			<tr>
				<th>카테고리No.</th>
				<td>${dto.getCategory_fk() }</td>
			</tr>
			<tr>
				<th>상품 이름</th>
				<td>${dto.getProducts_name() }</td>
			</tr>
			<tr>
				<th>제품 코드</th>
				<td>${dto.getEp_code_fk() }</td>
			</tr>
			<tr>
				<th>입고가</th>
				<td><fmt:formatNumber value="${dto.getInput_price() }" />원</td>
			</tr>
			<tr>
				<th>출고가</th>
				<td><fmt:formatNumber value="${dto.getOutput_price() }" />원</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td><fmt:formatNumber value="${dto.getTrans_cost() }" />원</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><fmt:formatNumber value="${dto.getMileage() }" />포인트</td>
			</tr>
			<tr>
				<th>제조사</th>
				<td>${dto.getCompany() }</td>
			</tr>
		<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>상품에 대한 정보가 없습니다</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="상품수정" onclick="location.href='product_update.do?num=${dto.getPnum() }'">
					<input type="button" value="상품삭제" onclick="if(confirm('정말로 삭제하시겠습니까')) { location.href='product_delete.do?num=${dto.getPnum() }' } else { return; }">
					<input type="button" value="목록으로" onclick="location.href='product_list.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
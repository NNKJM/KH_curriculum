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
		<c:set var="dto" value="${Modify }" />
		<hr width="50%" color="marmoon">
			<h3>PRODUCTS 테이블 상품 수정 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/product_update_ok.do">
			<table border="1" cellspacing="0" width="450">
				<input type="hidden" name="pnum" value="${dto.getPnum() }">
				<tr>
					<th>카테고리 번호</th>
					<td><input name="category_fk" value="${dto.getCategory_fk() }" readonly></td>
				</tr>
				<tr>
					<th>상품 이름</th>
					<td><input name="products_name" value="${dto.getProducts_name() }" readonly></td>
				</tr>
				<tr>
					<th>입고가</th>
					<td><input type="number" name="input_price" value="${dto.getInput_price() }"></td>
				</tr>
				<tr>
					<th>출고가</th>
					<td><input type="number" name="output_price" value="${dto.getOutput_price() }"></td>
				</tr>
				<tr>
					<th>배송비</th>
					<td><input type="number" name="trans_cost" value="${dto.getTrans_cost() }"></td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td><input type="number" name="mileage" value="${dto.getMileage() }"></td>
				</tr>
				<tr>
					<th>제조사</th>
					<td><input name="company" value="${dto.getCompany() }" readonly></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="제품수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
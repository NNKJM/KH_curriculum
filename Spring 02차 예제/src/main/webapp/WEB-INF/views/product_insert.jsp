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
	   <hr width="35%" color="skyblue">
	      <h3>PRODUCTS 테이블 제품 등록 폼 페이지</h3>
	   <hr width="35%" color="skyblue">
	   <br>
	   
	   <form method="post"
	        action="<%=request.getContextPath() %>/product_insert_ok.do">
	   
	      <table border="1" cellspacing="0" width="300">
	         <c:set var="list" value="${cartList }" />
	         
	         <tr>
	            <th>카테고리 코드</th>
	            <td>
	               <select name="category_fk">
	                  <c:if test="${empty list }">
	                     <option value="">:::카테고리 코드 없음:::</option>
	                  </c:if>
	                  
	                  <c:if test="${!empty list }">
	                     <c:forEach items="${list }" var="dto">
	                        <option value="${dto.getCategory_code() }">
	                             ${dto.getCategory_name() } [${dto.getCategory_code() }]</option>
	                     </c:forEach>
	                  </c:if>
	               </select>
	            </td>
	         </tr>
	         
	         <tr>
	            <th>제품명</th>
	            <td> <input name="products_name"> </td>
	         </tr>
	         
	         <tr>
	            <th>제품 코드</th>
	            <td> <input name="ep_code_fk"> </td>
	         </tr>
	         
	         <tr>
	            <th>제품  입고 가격</th>
	            <td> <input name="input_price"> </td>
	         </tr>
	         
	         <tr>
	            <th>제품 출고 가격</th>
	            <td> <input name="output_price"> </td>
	         </tr>
	         
	         <tr>
	            <th>제품 배송비</th>
	            <td> <input name="trans_cost"> </td>
	         </tr>
	         
	         <tr>
	            <th>제품 마일리지</th>
	            <td> <input name="mileage"> </td>
	         </tr>
	         
	         <tr>
	            <th>제품 제조사</th>
	            <td> <input name="company"> </td>
	         </tr>
	         
	         <tr>
	            <td colspan="2" align="center">
	               <input type="submit" value="제품등록">&nbsp;&nbsp;&nbsp;
	               <input type="reset" value="다시작성">
	            </td>
	         </tr>
	      </table>
	   
	   </form>
	
	</div>
</body>
</html>
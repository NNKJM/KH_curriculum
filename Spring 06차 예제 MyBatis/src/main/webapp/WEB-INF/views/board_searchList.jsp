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
	   <hr width="50%" color="red">
	      <h3>BOARD 테이블 게시물 전체 리스트 페이지</h3>
	   <hr width="50%" color="red">
	   <br>
	   
	   <table border="1" cellspacing="0" width="500">
	      <tr>
	         <th>게시글 No.</th> <th>글 제목</th> <th>작성자</th> <th>조회수</th> <th>작성일자</th>
	      </tr>
	      
	      <c:set var="list" value="${searchPageList }" />
	      <c:set var="paging" value="${Paging }" />
	      
	      <c:if test="${!empty list }">
	         <c:forEach items="${list }" var="dto">
	            <tr>
	               <td> ${dto.getBoard_no() } </td>
	               <td> 
	                  <a href="<%=request.getContextPath() %>/board_content.do?no=${dto.getBoard_no() }&page=${paging.page }"> ${dto.getBoard_title() } </a></td>
	               <td> ${dto.board_writer } </td>
	               <td> ${dto.board_hit } </td>
	               <td> ${dto.board_date.substring(0, 10) } </td>
	            </tr>
	         </c:forEach>
	      </c:if>
	      
	      <c:if test="${empty list }">
	         <tr>
	            <td colspan="5" align="center">
	               <h3>검색된 게시물 목록이 없습니다</h3>
	            </td>
	         </tr>
	      </c:if>
	      
	      <tr>
	         <td colspan="5" align="right">
	            <input type="button" value="글쓰기"  onclick="location.href='board_write.do'">
	         </td>
	      </tr>
	      
	   </table>
	   
	   <br>
	   
	   <%-- 페이징 처리 부분 --%>
	   <c:if test="${paging.getPage() > paging.getBlock() }">
	      <a href="board_list.do?page=1&field=${paging.field }&keyword=${paging.keyword }">[First]</a>
	      <a href="board_list.do?page=${paging.startBlock - 1 }&field=${paging.field }&keyword=${paging.keyword }">{Prev]</a>
	   </c:if>
	   
	   <c:forEach begin="${paging.getStartBlock() }" end="${paging.getEndBlock() }" var="i">
	      <c:if test="${i == paging.getPage() }">
	         <b> <a href="board_list.do?page=${i }&field=${paging.field }&keyword=${paging.keyword }">[${i }]</a></b>
	      </c:if>
	   
	   	  <c:if test="${i != paging.getPage() }">
	         <a href="board_list.do?page=${i }&field=${paging.field }&keyword=${paging.keyword }">[${i }]</a>
	      </c:if>
	   </c:forEach>

	   <c:if test="${paging.getEndBlock() < paging.getAllPage() }">
	      <a href="board_list.do?page=${paging.endBlock + 1 }&field=${paging.field }&keyword=${paging.keyword }">[Next]</a>
	      <a href="board_list.do?page=${paging.allPage }&field=${paging.field }&keyword=${paging.keyword }">[Last]</a>
	   </c:if>
	   
	   <br> <br>
	   
	   <form method="post" action="<%=request.getContextPath() %>/board_search.do">
	   <input type="hidden" name="page" value="${paging.getPage() }">
	      <select name="field">
	         <option value="title">제목</option>
	         <option value="cont">내용</option>
	         <option value="title_cont">제목+내용</option>
	         <option value="writer">작성자</option>
	      </select>
	      
	      <input name="keyword">&nbsp;&nbsp;
	      <input type="submit" value="검색">
	   </form>
	   
	</div>

</body>
</html>
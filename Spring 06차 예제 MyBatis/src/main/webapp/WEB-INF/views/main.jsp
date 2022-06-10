<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

	<div align="center">
	   <hr width="50%" color="marmoon">
	      <h3>BOARD 테이블 메인 페이지</h3>
	   <hr width="50%" color="marmoon">
	   <br>
	   
	   <a href="<%=request.getContextPath() %>/board_list.do">[게시물 전체 목록]</a>
	</div>
	
</body>
</html>

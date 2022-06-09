<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<div align="center">
		<hr width="35%" color="red">
			<h3>메인 페이지</h3>
		<hr width="35%" color="red">
		<br>
		<a href="<%=request.getContextPath() %>/board_list.do">[게시판 열기]</a>
	</div>
</body>
</html>

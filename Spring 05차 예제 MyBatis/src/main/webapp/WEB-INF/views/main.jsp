<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Main</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="#222222">
			<h3>EMP 테이블 메인 페이지</h3>
		<hr width="50%" color="#222222">
		<br>
		<a href="<%=request.getContextPath() %>/emp_list.do">[부서 전체 목록]</a>
	</div>
</body>
</html>

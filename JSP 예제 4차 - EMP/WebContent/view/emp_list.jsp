<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<EmpDTO> emp = (List<EmpDTO>) request.getAttribute("eList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<hr width="30%" color="red">
		<h3>EMP TABLE LIST</h3>
		<hr width="30%" color="red">
		<br>
		<table border="1" cellspacing="0" width="425">
			<tr>
				<th>EmpNo.</th>
				<th>Name</th>
				<th>Job</th>
				<th>HireDate</th>
				<th>Salary</th>
				<th>DeptNo.</th>
			</tr>
			<%
				if (emp.size() != 0) {
					for (int i = 0; i < emp.size(); i++) {
						EmpDTO dto = emp.get(i);
				%>
			<tr>
				<td><%=dto.getEmpno()%></td>
				<td>
					<a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getEmpno() %>">
					<%=dto.getEname()%></a></td>
				<td><%=dto.getJob() %></td>
				<td><%=dto.getHiredate().substring(0,10) %></td>
				<td><%=dto.getSal() %></td>
				<td><%=dto.getDeptno() %></td>
			</tr>
			<% } // for END
					} else { %>
			<tr>
				<td colspan="6" align="center">
					<h3>THERE IS NO EMPLOYEE SEARCHED</h3>
				</td>
			</tr>
			<%	}	%>
			<tr>
				<td colspan="6" align="right">
					<input type="button" value="INSERT" onclick="location.href='insert.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
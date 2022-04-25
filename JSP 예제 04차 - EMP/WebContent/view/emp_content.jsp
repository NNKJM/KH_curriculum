<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EmpDTO cont = (EmpDTO)request.getAttribute("content");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="tomato">
			<h3>EMP DETAIL IMFORMATION</h3>
		<hr width="30%" color="tomato">
		<table border="1" cellspacing="0" width="400">
			<%
				if (cont != null) {
			%>
			<tr>
				<th>EMPNO</th>
				<td><%=cont.getEmpno()%></td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><%=cont.getEname()%></td>
			</tr>
			<tr>
				<th>JOB</th>
				<td><%=cont.getJob()%></td>
			</tr>
			<tr>
				<th>MANAGER</th>
				<td>
					<% if(cont.getMname().equals("")){ %>
					<% } else { %>
					<a href="<%=request.getContextPath() %>/content.do?no=<%=cont.getMgr() %>">
					<%=cont.getMname()%> [<%=cont.getMgr()%>]</a>
					<% }%>
				</td>
			</tr>
			<tr>
				<th>HIREDATE</th>
				<td><%=cont.getHiredate().substring(0,10)%></td>
			</tr>
			<tr>
				<th>SALARY</th>
				<td><%=cont.getSal()%></td>
			</tr>
			<tr>
				<th>COMM</th>
				<td><%=cont.getComm()%></td>
			</tr>
			<tr>
				<th>DeptNo</th>
				<td><%=cont.getDeptno()%></td>
			</tr>
			<%
				} else {
			%>
			<tr>
				<td colspan="2" align="center">
					<h3>Can't find this page</h3>
				</td>
			</tr>

			<%
				}
			%>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="Edit" 
					onclick="location.href='update.do?no=<%=cont.getEmpno()%>'">
				<input type="button" value="Delete" 
					onclick="if(confirm('Do you want to delete this employee's information?')){
						location.href='delete.do?no=<%=cont.getEmpno()%>'
					}else{return;}">
				<input type="button" value="List"
					onclick="location.href='select.do'">
				<input type="button" value="Back"
					onclick="history.go(-1)"></td>
			</tr>
			</table>
	</div>
</body>
</html>
<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("dList");
	List<EmpDTO> emp = (List<EmpDTO>)request.getAttribute("eList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="gray">
		<h3>EMP INSERT FORM</h3>
		<hr width="30%" color="gray">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/insert_ok.do">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>Empno</th>
					<td><input type="text" name="emp_empno"></td>
				</tr>
				<tr>
					<th>Ename</th>
					<td><input type="text" name="emp_ename"></td>
				</tr>
				<tr>
					<th>Job</th>
					<td><input type="text" name="emp_job"></td>
				</tr>
				<tr>
					<th>Manager</th>
					<td><select name="emp_mgr">
						<% if(emp.size()==0) { %>
							<option value="">:::NONE:::</option>
						<% } else { 
							for(int i=0; i<emp.size(); i++){
								EmpDTO eDto = emp.get(i); %>
								<option value="<%=eDto.getEmpno() %>">
									<%=eDto.getEname() %>[<%=eDto.getEmpno() %>]
								</option>
							<%  }
						}	%>
					</select></td>
				</tr>
				<tr>
					<th>Sal</th>
					<td><input type="text" name="emp_sal"></td>
				</tr>
				<tr>
					<th>Comm</th>
					<td><input type="text" name="emp_comm"></td>
				</tr>
				<tr>
					<th>Deptno</th>
					<td><select name="emp_deptno">
						<% if(dept.size()==0){ %>
							<option value="">:::NONE:::</option>
							<% } else { 
							for(int i=0; i<dept.size(); i++){
								DeptDTO dDto = dept.get(i);
								%>
							<option value="<%=dDto.getDeptno()%>">
								<%=dDto.getDname() %>[<%=dDto.getDeptno() %>]
							</option>
							<%
							}
						} %>
					</select></td>
				</tr>
					<td colspan="2" align="center">
						<input type="submit" value="submit">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="reset">
					</td>
			</table>
		</form>
	</div>

</body>
</html>
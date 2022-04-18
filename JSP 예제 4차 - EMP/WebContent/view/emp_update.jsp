<%@page import="com.emp.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EmpDTO cont = (EmpDTO) request.getAttribute("modify");
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
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<input type="hidden" name="emp_no" value="<%=cont.getEmpno() %>">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>Name</th>
					<td><input type="text" name="emp_ename" value="<%=cont.getEname() %>" readonly></td>
				</tr>
				<tr>
					<th>Job</th>
					<td><input type="text" name="emp_job" value="<%=cont.getJob() %>"></td>
				</tr>
				<tr>
					<th>Manager</th>
					<td><select name="emp_mgr">
							<% if (emp.size() == 0) { %>
							<option value="">:::카테고리 없음:::</option>
							<% } else {
								for (int i = 0; i < emp.size(); i++) {
									EmpDTO eDto = emp.get(i);
									if (cont.getMgr() == eDto.getMgr()) { %>
										<option value="<%=eDto.getMgr()%>" selected>
									<%=eDto.getMname()%>[<%=eDto.getMgr()%>]
									<% } else { %> 
										<option value="<%=eDto.getMgr()%>" disabled>
										<%=eDto.getMgr()%>[<%=eDto.getMgr()%>] 
									<% }
									}
								} %>
					</select></td>
				</tr>
				<tr>
					<th>Sal</th>
					<td><input type="text" name="emp_sal" value="<%=cont.getSal() %>"></td>
				</tr>
				<tr>
					<th>Comm</th>
					<td><input type="text" name="emp_comm" value="<%=cont.getComm() %>"></td>
				</tr>
				<tr>
					<th>Deptno</th>
					<td><select name="dept_no">
							<% if (dept.size() == 0) { %>
							<option value="">:::카테고리 없음:::</option>
							<% } else {
								for (int i = 0; i < dept.size(); i++) {
									DeptDTO dDto = dept.get(i);
									if (cont.getDeptno() == dDto.getDeptno()) { %>
										<option value="<%=dDto.getDeptno()%>" selected>
									<%=dDto.getDeptno()%>[<%=dDto.getDeptno()%>]
									<% } else { %> 
										<option value="<%=dDto.getDeptno()%>" disabled>
										<%=dDto.getDname()%>[<%=dDto.getDeptno()%>] 
									<% }
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
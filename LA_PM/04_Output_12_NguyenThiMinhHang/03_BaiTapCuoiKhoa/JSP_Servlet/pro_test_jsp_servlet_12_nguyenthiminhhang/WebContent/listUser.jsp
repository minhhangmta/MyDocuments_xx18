<%@page import="java.util.ArrayList"%>
<%@page import="beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">List User</h2>
	<div align="center">
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Birthday</th>
				<th>Birthplace</th>
			</tr>
			<%
				ArrayList<User> list = (ArrayList<User>) request.getAttribute("listUser");
				for (User user : list) {
			%>
			<tr>
				<td><%=user.getId()%></td>
				<td><%=user.getName()%></td>
				<td><%=user.getBirthday()%></td>
				<td><%=user.getBirthplace()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<h2 align="center">List User</h2>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Birthday</th>
				<th>Birthplace</th>
			</tr>
			<c:forEach items="${listUser}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.birthday}</td>
					<td>${user.birthplace}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
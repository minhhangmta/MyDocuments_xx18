<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="manageuser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/view/css/style.css"
	rel="stylesheet" type="text/css" />
<title></title>
</head>
<body>
	<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
	<div>
		<div>
			<table>
				<tr>
					<td width="80%"><img
						src="${path}/view/img/logo-manager-user.gif" alt="Luvina" /></td>
					<td></td>
					<td align="left"><a
						href="${path}<%=Constant.LOGOUT_SERVLET%>">ログアウト</a>
						&nbsp; <a
						href="${path}<%=Constant.LISTUSER_SERVLET%>?type=default">トップ</a></td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
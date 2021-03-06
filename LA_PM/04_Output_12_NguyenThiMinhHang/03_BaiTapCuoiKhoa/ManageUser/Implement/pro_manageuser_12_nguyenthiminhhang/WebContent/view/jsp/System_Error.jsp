<%@page import="manageuser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath}/view/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/view/js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<c:import url="header.jsp"></c:import>
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form
		action="${pageContext.request.contextPath}${Constant.LISTUSER_SERVLET}"
		method="post" name="inputform">
		<table class="tbl_input" border="0" width="80%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="2">
					<div style="height: 50px"></div>
				</td>
			</tr>
			<tr>
				<!-- Start fix bug ID 50 - NguyenThiMinhHang 2017/11/1  -->
				<td align="center" colspan="2"><font color="red">${error}</font></td>
				<!-- End fix bug ID 50 - NguyenThiMinhHang 2017/11/1  -->
			</tr>
			<tr>
				<td align="center" colspan="2">
					<div style="height: 70px"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input class="btn" type="submit"
					value="OK" /></td>
			</tr>
		</table>
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<c:import url="footer.jsp"></c:import>
	<!-- End vung footer -->
</body>
</html>
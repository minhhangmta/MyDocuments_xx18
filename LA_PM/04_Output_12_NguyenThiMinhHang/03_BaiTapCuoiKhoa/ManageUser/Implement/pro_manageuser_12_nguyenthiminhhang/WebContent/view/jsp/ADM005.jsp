<%@page import="manageuser.properties.MessageProperties"%>
<%@page import="manageuser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/view/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/view/js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
	<!-- Begin vung header -->
	<c:import url="header.jsp"></c:import>
	<!-- End vung header -->

	<!-- Begin vung input-->
	<form action="#" method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">情報確認</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left">${fn:escapeXml(userInfor.loginName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left">${fn:escapeXml(userInfor.groupName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left">${fn:escapeXml(userInfor.fullName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left">${fn:escapeXml(userInfor.fullNameKana)}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left"><fmt:formatDate type="date"
										pattern="<%=Constant.FORMAT_DATE%>"
										value="${userInfor.birthday}" /></td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left">${fn:escapeXml(userInfor.email)}</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left">${fn:escapeXml(userInfor.tel)}</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onClick="hiddenTable()">日本語能力</a></th>
							</tr>
						</table>
						<div id="japan_table"
							style="display: none;">
							<table border="1" width="70%" class="tbl_input" cellpadding="4"
								cellspacing="0">
								<tr>
									<td class="lbl_left">資格:</td>
									<td align="left">${fn:escapeXml(userInfor.nameLevel)}</td>
								</tr>
								<tr>
									<td class="lbl_left">資格交付日:</td>
									<td align="left"><fmt:formatDate type="date"
											pattern="<%=Constant.FORMAT_DATE%>"
											value="${userInfor.startDate}" /></td>
								</tr>
								<tr>
									<td class="lbl_left">失効日:</td>
									<td align="left"><fmt:formatDate type="date"
											pattern="<%=Constant.FORMAT_DATE%>"
											value="${userInfor.endDate}" /></td>
								</tr>
								<tr>
									<td class="lbl_left">点数:</td>
									<td align="left">${fn:escapeXml(userInfor.total)}</td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 100px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<!-- button edit user -->
					<td><input class="btn" type="button" value="編集"
						onclick="javascript:window.location='${path}<%=Constant.ADD_USER_INPUT%>?tab=edit&id=${userId}'" /></td>
					<!-- button edit user -->
					<!-- button edit pass -->
					<td><input class="btn_pass" type="button" value="ChangePass"
						onclick="javascript:window.location='${path}<%=Constant.EDIT_PASS_SERVLET%>?id=${userId}'" /></td>
					<!-- button edit pass -->
					<!-- set param cho function confirmDelete -->
					<c:set var="deleteServletPath"
						value="<%=Constant.DELETE_USER_SERVLET%>"></c:set>
					<c:set var="url" value="${path}${deleteServletPath}?id=${userId}"></c:set>
					<jsp:useBean id="messageJA"
						class="manageuser.properties.MessageProperties" />
					<!-- set param cho function confirmDelete -->
					<!-- button delete user -->
					<td><input class="btn" type="button" value="削除"
						onclick="confirmDelete('${userId}', '${messageJA.getData('MSG004')}', '${url}')" /></td>
					<!-- button delete user -->
					<!-- button back -->
					<td><input class="btn" type="button" value="戻る"
						onclick="javascript:window.location='${path}<%=Constant.LISTUSER_SERVLET%>'" /></td>
					<!-- button back -->
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<c:import url="footer.jsp"></c:import>
	<!-- End vung footer -->
</body>
</html>
<%@page import="manageuser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form action="${path} <%=Constant.SYSTEM_ERROR%>" method="post"
		name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">
						情報確認<br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください
					</div>
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
								<td align="left">${userInfor.loginName}</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left">${userInfor.groupName}</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left">${userInfor.fullName}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left">${userInfor.fullNameKana}</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left">${userInfor.birthday}</td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left">${userInfor.email}</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left">${userInfor.tel}</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#">日本語能力</a></th>
							</tr>
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left">${userInfor.nameLevel}</td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left">${userInfor.startDate}</td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left">${userInfor.endDate}</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left">${userInfor.total}</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="200px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="OK"
						onclick="javascript:window.location='${path}<%=Constant.ADD_USER_OK%>" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="javascript:window.location='${path}<%=Constant.ADD_USER_INPUT%>?tab=back&keySession=${keySession}'" /></td>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->

	<!-- Begin vung footer -->
	<c:import url="footer.jsp"></c:import>
	<!-- End vung footer -->
</body>


</html>
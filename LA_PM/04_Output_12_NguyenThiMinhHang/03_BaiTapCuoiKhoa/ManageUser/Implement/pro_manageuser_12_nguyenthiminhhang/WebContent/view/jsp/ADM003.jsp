<%@page import="manageuser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form
		action="${path}<%=Constant.ADD_USER_VALIDATE%>${userInfor.userId > 0 ? '?tab=confirmEdit' : '?tab=confirmAdd'}"
		method="post" name="inputform">
		<input type="hidden" name="id" value="${userInfor.userId}">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<tr>
				<td class="errMsg">
					<div style="padding-left: 120px">
						<c:forEach items="${lstError}" var="error">
							&nbsp;${error}<br>
						</c:forEach>
					</div>
				</td>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input
									${userInfor.userId > 0 ? 'readonly="readonly"' : '' }
									class="txBox" type="text" name="username"
									value="${fn:escapeXml(userInfor.loginName)}" size="15"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="groupId">
										<option value="0" selected>選択してください</option>
										<c:forEach items="${listGroup}" var="group">
											<option value="${group.groupId}"
												${group.groupId == userInfor.groupId ? 'selected' : ''}>${group.groupName}</option>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName" value="${fn:escapeXml(userInfor.fullName)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullNameKana"
									value="${fn:escapeXml(userInfor.fullNameKana)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="yearBirthday">
										<c:forEach items="${listYear}" var="year">
											<c:if test="${year <= userInfor.year}">
												<option value="${year}"
													${year == userInfor.yearBirthday ? 'selected' : ''}>${year}</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="monthBirthday">
										<c:forEach items="${listMonth}" var="month">
											<option value="${month}"
												${month == userInfor.monthBirthday ? 'selected' : ''}>${month}</option>
										</c:forEach>
								</select>月 <select name="dayBirthday">
										<c:forEach items="${listDay}" var="day">
											<option value="${day}"
												${day == userInfor.dayBirthday ? 'selected' : ''}>${day}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInfor.email)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInfor.tel)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>

							<tr class="password_txt"
								style="display: ${userInfor.userId > 0 ? 'none;' : '' }">
								<td class="lbl_left"><font color="red">*</font> パスワード:</td>
								<td align="left"><input class="txBox" type="password"
									name="password" value="${fn:escapeXml(userInfor.passwords)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr class="password_txt"
								style="display: ${userInfor.userId > 0 ? 'none;' : '' }">
								<td class="lbl_left">パスワード（確認）:</td>
								<td align="left"><input class="txBox" type="password"
									name="confirmPassword"
									value="${fn:escapeXml(userInfor.confirmPassword)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>

							<tr>
								<th align="left" colspan="2"><a href="#"
									onClick="hiddenTable()">日本語能力</a></th>
							</tr>
						</table>
						<table id="japan_table" style="display: none;">
							<tr>
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="codeLevel">
										<option value="" selected>選択してください</option>
										<c:forEach items="${listJapan}" var="japan">
											<option value="${japan.codeLevel}"
												${japan.codeLevel == userInfor.codeLevel ? 'selected' : ''}>${japan.nameLevel}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="yearStartDate">
										<c:forEach items="${listYear}" var="year">
											<c:if test="${year <= userInfor.year}">
												<option value="${year}"
													${year == userInfor.yearStartDate ? 'selected' : ''}>${year}</option>
											</c:if>
										</c:forEach>
								</select>年 <select name="monthStartDate">
										<c:forEach items="${listMonth}" var="month">
											<option value="${month}"
												${month == userInfor.monthStartDate ? 'selected' : ''}>${month}</option>
										</c:forEach>
								</select>月 <select name="dayStartDate">
										<c:forEach items="${listDay}" var="day">
											<option value="${day}"
												${day == userInfor.dayStartDate ? 'selected' : ''}>${day}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="yearEndDate">
										<c:forEach items="${listYear}" var="year">
											<option value="${year}"
												${year == userInfor.yearEndDate ? 'selected' : ''}>${year}</option>
										</c:forEach>

								</select>年 <select name="monthEndDate">
										<c:forEach items="${listMonth}" var="month">
											<option value="${month}"
												${month == userInfor.monthEndDate ? 'selected' : ''}>${month}</option>
										</c:forEach>
								</select>月 <select name="dayEndDate">
										<c:forEach items="${listDay}" var="day">
											<option value="${day}"
												${day == userInfor.dayEndDate ? 'selected' : ''}>${day}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total" value="${userInfor.total}" size="5"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
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
					<td><input class="btn" type="submit" value="確認" /></td>
					<td><input class="btn" type="button" value="戻る"
						onclick="javascript:window.location='${path}<%=Constant.LISTUSER_SERVLET%>'" /></td>
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
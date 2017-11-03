<%@page import="manageuser.properties.MessageProperties"%>
<%@page import="manageuser.utils.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<!-- Begin vung header -->
	<c:import url="header.jsp"></c:import>
	<!-- End vung header -->

	<!-- Begin vung dieu kien tim kiem -->
	<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
	<form action="${path}<%=Constant.LISTUSER_SERVLET%>" method="post"
		name="mainform">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="fullName" value="${fn:escapeXml(fullName)}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:forEach items="${listGroup}" var="group">
										<option value="${group.groupId}"
											${group.groupId == group_id ? 'selected' : ''}>${group.groupName}</option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加"
								onclick="javascript:window.location='${path}/addUser.do?tab=add'" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="type" value="search">
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<c:choose>
		<c:when test="${totalRecord == 0}">
		${msg005}
		</c:when>
		<c:otherwise>
			<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
				width="80%">

				<tr class="tr2">
					<th align="center" width="20px">ID</th>
					<th align="left">氏名 <a
						href="${path}<%=Constant.LISTUSER_SERVLET%>?type=sort&typeSort=full_name">
							<!-- Start fix bug ID 47 – NguyenThiMinhHang 2017/11/1 --> <c:if
								test="${sortByName == 'ASC'}">
									▲▽
									</c:if> <c:if test="${sortByName == 'DESC'}">
									△▼
									</c:if>
					</a>
					</th>
					<th align="left">生年月日</th>
					<th align="left">グループ</th>
					<th align="left">メールアドレス</th>
					<th align="left" width="70px">電話番号</th>
					<th align="left">日本語能力 <a
						href="${path}<%=Constant.LISTUSER_SERVLET%>?type=sort&typeSort=code_level"><c:if
								test="${sortByCodeLevel == 'ASC'}">
									▲▽
									</c:if> <c:if test="${sortByCodeLevel == 'DESC'}">
									△▼
									</c:if></a>
					</th>
					<th align="left">失効日 <a
						href="${path}<%=Constant.LISTUSER_SERVLET%>?type=sort&typeSort=end_date"><c:if
								test="${sortByEndDate == 'ASC'}">
									▲▽
									</c:if> <c:if test="${sortByEndDate == 'DESC'}">
									△▼
									</c:if> <!-- End fix bug ID 47 – NguyenThiMinhHang 2017/11/1 --> </a>
					</th>
					<th align="left">点数</th>
				</tr>

				<c:forEach items="${listUser}" var="user">
					<tr>
						<td align="right"><a href="${path}/<%=Constant.ADM005%>">${user.userId}</a></td>
						<td>${user.fullName}</td>
						<td align="center"><fmt:formatDate type="date"
								pattern="yyyy/MM/dd" value="${user.birthday}" /></td>
						<td>${user.groupName}</td>
						<td>${user.email}</td>
						<td>${user.tel}</td>
						<td>${user.nameLevel}</td>
						<td align="center"><fmt:formatDate type="date"
								pattern="yyyy/MM/dd" value="${user.endDate}" /></td>
						<td align="right">${user.total}</td>
					</tr>
				</c:forEach>

			</table>
		</c:otherwise>
	</c:choose>
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table>
		<tr>
			<td class="lbl_paging"><c:if
					test="${currentPage > listPaging.size()}">
					<a
						href="${path}<%=Constant.LISTUSER_SERVLET%>?type=paging&page=${previousPage}">
						&lt;&lt; </a>&nbsp;
				</c:if> <c:forEach items="${listPaging}" var="page">
					<!-- Start fix bug ID 48 – NguyenThiMinhHang 2017/11/1 -->
					<c:choose>
						<c:when test="${page == currentPage}">
							<c:choose>
								<c:when
									test="${listPaging.indexOf(currentPage) == listPaging.size()-1 || currentPage == totalPage}">${page}</c:when>
								<c:otherwise>${page}|</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test="${page <= totalPage && totalPage > 1 }">
								<c:choose>
									<c:when
										test="${listPaging.indexOf(page)==listPaging.size()-1 || page == totalPage }">
										<a
											href="${path}<%=Constant.LISTUSER_SERVLET%>?type=paging&page=${page}">${page}
										</a>&nbsp;
									</c:when>
									<c:otherwise>
										<a
											href="${path}<%=Constant.LISTUSER_SERVLET%>?type=paging&page=${page}">${page}
										</a>|
								</c:otherwise>
								</c:choose>
							</c:if>

						</c:otherwise>
					</c:choose>
					<!-- End fix bug ID 48 – NguyenThiMinhHang 2017/11/1 -->
				</c:forEach> <c:if
					test="${listPaging.size() < totalPage && currentPage < totalPage }">
					<a
						href="${path}<%=Constant.LISTUSER_SERVLET%>?type=paging&page=${nextPage}">
						&gt;&gt; </a>
				</c:if></td>
		</tr>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<c:import url="footer.jsp"></c:import>
	<!-- End vung footer -->

</body>
</html>
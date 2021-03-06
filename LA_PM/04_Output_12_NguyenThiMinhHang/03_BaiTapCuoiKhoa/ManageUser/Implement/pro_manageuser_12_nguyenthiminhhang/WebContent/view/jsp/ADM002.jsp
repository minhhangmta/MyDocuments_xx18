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
	<form action="${path}${Constant.LISTUSER_SERVLET}" method="post"
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
							<td align="left" width="80px"><select name="groupId">
									<option value="0">全て</option>
									<c:forEach items="${listGroup}" var="group">
										<option value="${group.groupId}"
											${group.groupId == groupId ? 'selected' : ''}>${fn:escapeXml(group.groupName)}</option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加"
								onclick="sendToAnotherController('${path}${Constant.ADD_USER_INPUT}')" />
								<input class="btn_export" type="button" value="輸出のcsv"
								onclick="sendToAnotherController('${path}${Constant.EXPORT_FILE}?download=userInfor')" />
							</td>
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
						href="${path}${Constant.LISTUSER_SERVLET}?type=sort&typeSort=full_name&sort=${sortByName=='ASC' ? 'DESC' : 'ASC'}">
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
					<th align="left" width="100px">電話番号</th>
					<th align="left">日本語能力 <a
						href="${path}${Constant.LISTUSER_SERVLET}?type=sort&typeSort=code_level&sort=${sortByCodeLevel=='ASC' ? 'DESC' : 'ASC'}"><c:if
								test="${sortByCodeLevel == 'ASC'}">
									▲▽
									</c:if> <c:if test="${sortByCodeLevel == 'DESC'}">
									△▼
									</c:if></a>
					</th>
					<th align="left">失効日 <a
						href="${path}${Constant.LISTUSER_SERVLET}?type=sort&typeSort=end_date&sort=${sortByEndDate=='ASC' ? 'DESC' : 'ASC'}"><c:if
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
						<td align="right"><a
							href="${path}${Constant.DETAIL_SERVLET}?type=edit&id=${user.userId}">${user.userId}</a></td>
						<td>${fn:escapeXml(user.fullName)}</td>
						<td align="center"><fmt:formatDate type="date"
								pattern="${Constant.FORMAT_DATE}" value="${user.birthday}" /></td>
						<td>${fn:escapeXml(user.groupName)}</td>
						<td>${fn:escapeXml(user.email)}</td>
						<td>${fn:escapeXml(user.tel)}</td>
						<td>${fn:escapeXml(user.nameLevel)}</td>
						<td align="center"><fmt:formatDate type="date"
								pattern="${Constant.FORMAT_DATE}" value="${user.endDate}" /></td>
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
						href="${path}${Constant.LISTUSER_SERVLET}?type=paging&page=${previousPage}">
						&lt;&lt; </a>&nbsp;
				</c:if> <c:forEach items="${listPaging}" var="page">
					<!-- Start fix bug ID 48 – NguyenThiMinhHang 2017/11/1 -->
					<c:choose>
						<c:when test="${page == currentPage  && totalPage > 1 }">
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
											href="${path}${Constant.LISTUSER_SERVLET}?type=paging&page=${page}">${page}
										</a>&nbsp;
									</c:when>
									<c:otherwise>
										<a
											href="${path}${Constant.LISTUSER_SERVLET}?type=paging&page=${page}">${page}
										</a>|
								</c:otherwise>
								</c:choose>
							</c:if>

						</c:otherwise>
					</c:choose>
					<!-- End fix bug ID 48 – NguyenThiMinhHang 2017/11/1 -->
				</c:forEach> <c:if test="${listPaging.get(listPaging.size()-1) < totalPage}">
					<a
						href="${path}${Constant.LISTUSER_SERVLET}?type=paging&page=${nextPage}">
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
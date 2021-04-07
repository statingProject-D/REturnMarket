<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Approval</title>
<style>
	table {
		text-align: center;
	}
	.none {
		text-align: center;
	}
</style>
</head>
<body>
	<table class="table">
		<c:choose>
		<c:when test="${voList ne null}">
		<tr>
			<td class="col-2">아이디</td>
			<td class="col-2">이름</td>
			<td class="col-2">전화번호</td>
			<td class="col-2">이메일</td>
			<td class="col-1">등급</td>
			<td class="col-1">승인</td>
			<td class="col-2">거절&삭제</td>
		</tr>
		<c:forEach var="admin" items="${voList}" varStatus="status">
		<tr>
			<td>${admin.id}</td>
			<td>${admin.name}</td>
			<td>${admin.phone}</td>
			<td>${admin.email}</td>
			<td>${admin.grade}</td>
			<td><a href="agreeAdmin.ad?id=${admin.id }" class="btn btn-success">승인</a></td>
			<td><a href="rejectAdmin.ad?id=${admin.id }" class="btn btn-danger">거절&삭제</a></td>
		</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<h1 class="none">승인이 필요한 유저가 존재하지 않습니다.</h1>
		</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
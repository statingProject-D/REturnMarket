<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.table {
		text-align: center;
		cursor: pointer;
	}
	.groupCate {
		display: flex;
		justify-content: center;
	}
	.none {
		text-align: center;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<form action="searchPd.ad" method="post" class="form-control search-bar">
		<span class="col-2">제품명 검색 : </span>
		<input type="text" name="search" class="col-8 search__input form-control" placeholder="품명을 입력해 주세요.">
		<input type="submit" class="col-2 btn btn-primary" value="검색">
	</form>
	
	<c:choose>
	<c:when test="${voList ne null}">
	<table class="table table-hover">
		<tr>
			<td class="col-2">상품명</td>
			<td class="col-2">등록유저</td>
			<td class="col-4">상품 분류</td>
			<td class="col-2">직거래 가격</td>
			<td class="col-2">경매 마감일</td>
		</tr>
		<c:forEach var="pd" items="${voList }" varStatus="status">
		<tr onclick="location.href='pdView.ad?pdNum=${pd.pdNum}'">	
			<td>${pd.pdName }</td>
			<td>${pd.user_id }</td>
			<td><span class="groupCate">${pd.pdGroup}&nbsp; - &nbsp;${pd.pdCategory }</span></td>
			<td>${pd.auDirectPrice }</td>
			<td>${pd.auEndDay }</td>
		</tr>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
		<h1 class="none">등록된 상품이 존재하지 않습니다.</h1>
	</c:otherwise>
	</c:choose>
</body>
</html>
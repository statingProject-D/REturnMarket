<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Infomation</title>
<style>
	.search-bar {
		margin: 5px 0;
		display: flex;
	}	
	.search-bar .search__input {
		width: 65%;
		display: inline;
		margin: 0 15px;
	}
	.table {
		overflow-y: auto;
		text-align: center;
		cursor: pointer;
	}
	.none {
		text-align: center;
		margin-right: 15px;
	}
</style>
</head>
<body>
	<form action="searchUser.ad" method="post" class="form-control search-bar">
		<span class="col-2">아이디 검색 : </span>
		<input type="text" name="search" class="col-8 search__input form-control" placeholder="아이디를 입력해 주세요.">
		<input type="submit" class="col-2 btn btn-primary" value="검색">
	</form>
	
	<c:choose>
	<c:when test="${voList ne null }">
	<table class="table table-hover">
		<tr>
			<td class="col-1">아이디</td>
			<td class="col-1">비밀번호</td>
			<td class="col-1">이름</td>
			<td class="col-1">전화번호</td>
			<td class="col-2">이메일</td>
			<td class="col-1">등급</td>
			<td class="col-2">주소</td>
			<td class="col-1">이메일 여부</td>
			<td class="col-1">수정</td>
			<td class="col-1">삭제</td>
		</tr>
		<c:forEach var="user" items="${voList }" varStatus="status">
		<tr>
			<td>${user.user_id }</td>
			<td>${user.pw }</td>
			<td>${user.name }</td>
			<td>${user.phone }</td>
			<td>${user.email }</td>
			<td>${user.grade }</td>
			<td>${user.addrNum} ${user.addr1 } ${user.addr2}</td>
			<td>${user.emailChk }</td>
			<td><a href="modiUser.ad?user_id=${user.user_id}" class="btn btn-info">수정</a></td>
			<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#askDelete">삭제</button></td>
		</tr>
		
		<!-- modal을 사용한 취소 물어보기 -->
		<div class="modal fade" id="askDelete" tabindex="-1" role="dialog" aria-labelledby="askDeleteLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="askDeleteLabel">이용자 정보 삭제</h5>
						<button type="button" class="close btn" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true"><i class="fas fa-times"></i></span>
						</button>
					</div>
					<div class="modal-body">정말 이용자 정보를 삭제하시겠습니까?</div>
					<div class="modal-footer">
						<a href="delUser.ad?user_id=${user.user_id}" class="btn btn-primary">삭제하기</a>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
	</table>
	</c:when>
	<c:otherwise>
		<h1 class="none">이용자가 존재하지 않습니다.</h1>
	</c:otherwise>
	</c:choose>
</body>
</html>
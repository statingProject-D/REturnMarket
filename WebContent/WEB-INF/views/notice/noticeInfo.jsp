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
	.writeBtn {
		width: 100%;
	}
	.none {
		text-align: center;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<a href="writeNoticed.ad" class="btn writeBtn btn-primary">글쓰기</a>
	<form action="searchNotice.ad" method="post" class="form-control search-bar">
		<span class="col-2">공지사항 검색 : </span>
		<input type="text" name="search" class="col-8 search__input form-control" placeholder="공지제목을 입력해 주세요.">
		<input type="submit" class="col-2 btn btn-primary" value="검색">
	</form>
	
	<table class="table table-hover">
		<c:choose>
			<c:when test="${voList ne null}">
				<tr>
					<td>글 번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>삭제</td>
				</tr>
				<c:forEach var="notice" items="${voList}" varStatus="status">
				<tr>
					<td onclick="location.href='noticeView.ad?no=${notice.no}';">${notice.no }</td>
					<td onclick="location.href='noticeView.ad?no=${notice.no}';">${notice.title}</td>
					<td onclick="location.href='noticeView.ad?no=${notice.no}';">${notice.user_id}</td>
					<td onclick="location.href='noticeView.ad?no=${notice.no}';">${notice.date}</td>
					<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#askDelete">삭제</button></td>
				</tr>
				<!-- modal을 사용한 취소 물어보기 -->
				<div class="modal fade" id="askDelete" tabindex="-1" role="dialog" aria-labelledby="askDeleteLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="askDeleteLabel">공지사항 삭제</h5>
								<button type="button" class="close btn" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true"><i class="fas fa-times"></i></span>
								</button>
							</div>
							<div class="modal-body">정말 공지사항을 삭제하시겠습니까?</div>
							<div class="modal-footer">
								<a href="noticeDel.ad?no=${notice.no}" class="btn btn-primary">삭제하기</a>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h1 class="none">공지사항이 존재하지 않습니다.</h1>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>
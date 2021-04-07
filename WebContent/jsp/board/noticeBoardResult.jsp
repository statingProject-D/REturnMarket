<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="pageList">
		<table class="table table-hover">
			<tr>
				<td class="col-1">번호</td>
				<td class="col-3">작성자</td>
				<td class="col-5">제목</td>
				<td class="col-3">날짜</td>
			</tr>
		<c:forEach var="notice" items="${noticeList }" varStatus="status">
			<tr onclick="location.href='noticeView.bo?no=${notice.no }';" class="linkTag">
				<td class="col-1">${notice.no }</td>
				<td class="col-3">${notice.user_id }</td>
				<td class="col-5">${notice.title }</td>
				<td class="col-3">${notice.date }</td>
			</tr>
		</c:forEach>
		</table>

		<c:if test="${pageInfo ne null }">
			<c:choose>
				<c:when test="${pageInfo.nowPage <= 1 }">
					<span class="prev">[Prev]&nbsp;</span>
				</c:when>
				<c:otherwise>
					<a href="noticeBoard.bo?page=${pageInfo.nowPage - 1 }" class="prev">[Prev]</a>
				</c:otherwise>
			</c:choose>
	
			<c:forEach var="pageNum" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<c:if test="${pageNum eq pageInfo.nowPage }">
					<span class="pageNum">${pageNum}</span>
				</c:if>
				<c:if test="${pageNum ne pageInfo.nowPage }">
					<a href="noticeBoard.bo?page=${pageNum}" class="pageNum">${pageNum}</a>
				</c:if>
			</c:forEach>
			
			<c:choose>
				<c:when test="${pageInfo.nowPage >= pageInfo.maxPage }">
					<span class="next">[Next]</span>
				</c:when>
				<c:otherwise>
					<a href="noticeBoard.bo?page=${pageInfo.nowPage + 1}" class="next">[Next]</a>
				</c:otherwise>
			</c:choose>
		</c:if>
		
	</div>
	<a href="noticeBoard.bo" class="btn btn-primary">공지사항 Main</a>

</body>
</html>
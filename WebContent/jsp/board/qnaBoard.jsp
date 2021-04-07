<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- Fontawesome css link -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
  <!-- BootStrip css -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
  <link rel="stylesheet" href="css/notice.css">
  <style>
    
  </style>
  <title>Add Product</title>
</head>
<body>
  <div class="screen">
    <div class="screenForm">
      <main class="main">
        <div class="logo">
          <a href="home.us">
     		 <img src="img/logo.png" class="logo-img"><span class="logo-title">REturn Market</span>
   		  </a>
        </div>

        <h1>QnA</h1>
        <div class="notice-board__container">
        	<form action="qnaSearch.bo" method="post" class="form-control">
        		<input type="search" name="searchWord" class="form-control searchbar" placeholder="제목을 입력해주세요." />
        		<input type="hidden" name="pageNum" value="${pageInfo.nowPage }" />
	            <button type="submit" class="search-icon"><i class="fas fa-search"></i></button>
        	</form>
	        
        	<div class="noticeBoardResult form-control">
	        	<div id="pageList">
				<table class="table table-hover">
					<tr>
						<td class="col-1">번호</td>
						<td class="col-3">작성자</td>
						<td class="col-4">제목</td>
						<td class="col-2">날짜</td>
						<td class="col-2">조회수</td>
					</tr>
					<c:forEach var="qna" items="${qnaList }" varStatus="status">
					<tr onclick="location.href='qnaView.bo?no=${qna.no }';" class="linkTag">
						<td class="col-1">${qna.no }</td>
						<td class="col-3">${qna.user_id }</td>
						<td class="col-4">${qna.title }</td>
						<td class="col-2">${qna.date }</td>
						<td class="col-2">${qna.viewCount }</td>
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
		<a href="qnaBoard.bo" class="btn btn-primary">QnA Main</a>
		<a href="writeQna.bo" class="btn btn-success">글 쓰기</a>
       	</div>
       </div>
      </main>

      <footer class="footer">
        <div class="team-info">
          <p>&copy; 제작팀: ShinHeung</p>
          <p>Made with : Dong Woo Kim <i class="fas fa-times i-multi"></i> Sang Mok Chae</p>
          <p>Team github <a href="https://github.com/statingProject-D"><i class="fab fa-github"></i></a></p>
        </div>
      </footer>
      
      <div class="navigate">
        <div class="circle-bg">
          <div class="nav-icons">
            <a href="home.us" class="nav-icon col-1 col-offset-1">
              <i class="fas fa-home"></i>
              <p>Home</p>
            </a>
            <a href="productList.pd" class="nav-icon col-3 col-offset-1">
              <i class="fas fa-list-ul"></i>
              <p>Categories</p>
            </a>
            <a href="myPdList.pd" class="nav-icon col-3 col-offset-1">
              <i class="fab fa-telegram-plane"></i>
              <p>MyList</p>
            </a>
            <a href="userDetail.us" class="nav-icon col-1 col-offset-1">
              <i class="far fa-user"></i>
              <p>User</p>
            </a>
          </div>
          <div class="circle-bg__sm">
            <a href="uploadPd.pd" class="nav-icon upload">
              <i class="fas fa-plus"></i>
              <p>Add</p>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- js -->

  <!-- bootstrip js link -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
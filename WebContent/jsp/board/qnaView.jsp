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
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
   <!--summernote css-->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
  <link rel="stylesheet" href="css/qnaView.css">
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

        <h1 class="notice-view__title">QnA</h1>
        <div class="notice-view">
        	<table class="table">
        		<tr>
        			<th class="notice__title col-3 td__left">글 제목</th>
        			<td colspan="3" class="col-10 notice__title td__left">${qnaBean.title }</td>
        			<th class="col-2 td__left">조회수</th>
        			<td class="col-2 td_left">${qnaBean.viewCount}</td>
        		</tr>
        		<tr>
        			<th class="no col-3 td__left">글 번호</th>
        			<td class="col-1 td__left">${qnaBean.no }</td>
        			<th class="witer col-2 td__left">작성자</th>
        			<td class="col-2 td__left">${qnaBean.user_id }</td>
        			<th class="date col-2 td__left">작성일</th>
        			<td class="col-2 td__left">${qnaBean.date}</td>
        		</tr>
        		<tr>
        			<th class="col-2 td__left"><span class="content-sub">내용</span></th>
        			<td class="col-10" colspan="5"><textarea id="summernote">${qnaBean.content }</textarea></td>
        		</tr>
        	</table>
         
          <c:if test="${user_id eq qnaBean.user_id }">
            <div class="btn-group">
              <a href="modiQna.bo?no=${qnaBean.no }" class="modiQna btn btn-success">수정</a>
              <button class="delQna btn btn-danger" onclick="askDelQna();">삭제</button>
            </div>
          </c:if>

          <div class="comment-container">
          	<form action="uploadComment.bo?no=${qnaBean.no }" method="post" class="form-control comment-table">
              <table class="table table-bordered">
                <tr>
                  <td class="col-3 cmt">${user_id}</td>
                  <td class="col-7 cmt"><input type="text" name="content" class="form-control"></td>
                  <td class="col-2 cmt"><input type="submit" class="comment-btn" value="Commit"></td>
                </tr>
                <c:forEach var="cmt" items="${cmtList }" varStatus="status">
	                <tr>
	                  <td class="cmt">${cmt.user_id }<br/>
	                  <c:if test="${user_id eq cmt.user_id }">
                  		<a href="deletCmt.bo?cmtNo=${cmt.cmt_no}&no=${qnaBean.no}">[삭제]</a>
              		  </c:if>
	                  </td>
	                  <td class="cmt">${cmt.content }</td>
	                  <td class="cmt">${cmt.date }</td>
	                </tr>
                </c:forEach>
	            </table>
          	</form>
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
  <script>
  	const askDelQna = () => {
	  	var askDel = confirm("정말 삭제하시겠습니까?");
	  	
	  	if(!askDel) {
	  		alert('뒤로 돌아갑니다.');
	  	} else {
	  		alert('성공적으로 삭제했습니다.');
	  		location.href="delQna.bo?no=" +${qnaBean.no };
	  	}
  	}
  </script>
  <!-- summernote js -->
  <script>
	  $('#summernote').summernote({
	      tabsize: 2,
	      height: 400
	      }); 
  </script>
</body>
</html>
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
  <link rel="stylesheet" href="css/product-list.css">
  <title>Product List</title>
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

        <!-- hidden -->
        <div class="hidden-logo fadeout" id="fadeout">
          <a href="home.jsp" class="hidden-logo-title__box">
            <span class="hidden-logo-title">REturn Market</span>
          </a>
          <label for="hidden-search">
            <i class="fas fa-search i-hidden-search"></i>
          </label>
          <form action="searchPd.pd" method="get" class="hidden-search-form">
            <input type="search" class="form-control hidden-search-bar" id="searcgPdName" name="searchPdName" placeholder="검색어를 입력하세요.">
          </form>
        </div>
          
        <div class="list__container">
          <div class="pick-header">
            <form action="searchPd.pd" method="get" class="search-form">
            <select class="bigCategory form-control input-sm" name="bgCg" required>
              <option value="">-대분류 선택-</option>
              <option value="중고">중고</option>
              <option value="미개봉">미개봉</option>
            </select>
            <select class="midCategory form-control input-sm" name="smCg" required>
              <option value="">-중분류 선택-</option>
              <option value="전자제품">전자제품</option>
              <option value="주방용품">주방용품</option>
              <option value="잡화물품">잡화용품</option>
            </select>
              <input type="search" name="searchPdName" class="form-control search-product__name" placeholder="제목을 입력하세요."/>
              <input type="submit" class="btn btn-primary" value="검색">
              <input type="button" class="btn btn-info" value="전체보기" onclick="location.href='productList.pd';" />
            </form>
          </div>
        </div>

        <div class="main-product__list" id="main-product__list">
          <div class="remarket-info__box">
            <p class="remarket-info">REturnMarket은 사이트만 재공해 드릴 뿐입니다.</p>
          </div>
          <c:choose>
          <c:when test="${pdList ne null}">
          <c:forEach var="pd" items="${pdList }" varStatus="status">
		     <a href="pdView.pd?pdNum=${pd.pdNum }">
		         <table class="table pdTable">
		            <tr>
		              <td>제목</td>
		              <td>${pd.pdName }</td>
		            </tr>
		          	<tr>
		          	<td colspan="2">
		              <img src="upload/${pd.pdImg }" class="pdImg"/>
		          	</td>
		          </tr>
		          <tr>
		         	<td>판매자</td>
		         	<td>${pd.user_id }</td>
		          </tr>
		          <tr>
		          	<td>물건수량</td>
		          	<td>${pd.pdCount }</td>
		          </tr>
		          <tr>
		          	<td>중고/미개봉</td>
		          	<td>${pd.pdGroup }</td>
		          </tr>
		          <tr>
		          	<td>물건분류</td>
		          	<td>${pd.pdCategory }</td>
		          </tr>
		          <tr>
		          	<td>경매마감일</td>
		          	<td>${pd.auEndDay }</td>
		          </tr>
		          <tr>
		          	<td>조회수</td>
		          	<td>${pd.pdViewCount }</td>
		          </tr>
	            </table>
          	 </a>
          </c:forEach>
          </c:when>
          <c:otherwise>
          	<h1 class="search__nonValue">검색결과를 만족하는 게시물이 없습니다.</h1>
          </c:otherwise>
          </c:choose>

          <a href="#main-product__list" class="topBtn">Top</a>
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

  <!-- bootstrip js link -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

  <script src="js/hidden-search-bar.js"></script>
</body>
</html>
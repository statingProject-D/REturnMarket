<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <link rel="stylesheet" href="css/index.css">
  <title>Home</title>
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
          
        <form action="searchPd.pd" class="seachForm" method="get">
          <label for="searchPd">
            <i class="fas fa-search i-search"></i>
          </label>
          <input type="search" class="form-control search-bar" id="searchPdName" name="searchPdName" placeholder="검색어를 입력하세요.">
        </form>
        <div class="btn-group">
          <!-- 버튼은 무슨용도로 할것인가? -->
          <button class="addAction btn" type="button" onclick="location.href='uploadPd.pd'">
            <i class="fas fa-street-view"></i>
            <span>상품등록</span>
          </button>
          <button class="dealDetail btn" type="button" onclick="location.href='myPdList.pd'">
            <i class="fas fa-receipt"></i>
            <span>상품내역</span>
          </button>
        </div>
        
        <div class="homeMenu">
          <div class="usedGoods">
            <a href="productList.pd" class="topBtn">
              <i class="fas fa-clipboard-list"></i>
              <p class="topBtn__subtitle">거래 물품</p>
            </a>
          </div>
          <div class="usedGoods">
            <a href="jjimList.pd" class="topBtn">
              <i class="fab fa-gratipay"></i>
              <p class="topBtn__subtitle">찜한 상품</p>
            </a>
          </div>
          <div class="randomGoods">
            <a href="qnaBoard.bo" class="topBtn">
              <i class="far fa-question-circle"></i>
              <p class="topBtn__subtitle">QnA</p>
            </a>
          </div>
        </div>
        
        <div class="slideshow-container">
            
          <!-- Full-width images with number and caption text -->
          <div class="mySlides fade">
            <div class="numbertext">1 / 5</div>
            <img src="https://s3.ap-northeast-2.amazonaws.com/univ-20slab/FileData/Board/Content/202003/37517/883727f0-de5b-4fd4-8ef1-a428d609a53a.png" style="width:100%">
            <div class="adText">REturn Market</div>
          </div>
          <div class="mySlides fade">
            <div class="numbertext">2 / 5</div>
            <img src="https://platum.kr/wp-content/uploads/2019/12/wise.png" style="width:100%">
            <div class="adText">REturn Market</div>
          </div>
          <div class="mySlides fade">
            <div class="numbertext">3 / 5</div>
            <img src="https://www.techm.kr/news/photo/202012/78803_74684_3152.png" style="width:100%">
            <div class="adText">REturn Market</div>
          </div>
          <div class="mySlides fade">
            <div class="numbertext">4 / 5</div>
            <img src="http://www.sisajournal-e.com/news/photo/first/201709/img_173549_1.png" style="width:100%">
            <div class="adText">REturn Market</div>
          </div>
          <div class="mySlides fade">
            <div class="numbertext">5 / 5</div>
            <img src="https://i.pinimg.com/originals/f5/03/44/f5034480434469b6a74bfa997f2834a6.jpg" style="width:100%">
            <div class="adText">REturn Market</div>
          </div>
              
              <!-- Next and previous buttons -->
          <a class="prev" onclick="moveSlides(-1)">&#10094;</a>
          <a class="next" onclick="moveSlides(1)">&#10095;</a>
          
          <div style="text-align:center">
            <span class="dot" onclick="currentSlide(0)"></span>
            <span class="dot" onclick="currentSlide(1)"></span>
            <span class="dot" onclick="currentSlide(2)"></span>
            <span class="dot" onclick="currentSlide(3)"></span>
            <span class="dot" onclick="currentSlide(4)"></span>
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

  
  <!-- bootstrip js link -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

  <!-- js -->
  <script src="js/ad.js"></script>
  <script src="js/hidden-search-bar.js"></script>
</body>
</html>
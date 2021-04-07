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
  <link rel="stylesheet" href="css/login.css">
  <title>Login</title>
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

        <div class="login__container">
          <form action="loginAction.us" method="post" class="loginForm ">
            <label for="id">ID : </label>
            <input type="text" name="user_id" id="user_id" class="form-control" placeholder="아이디를 입력하세요." required>
            <label for="pw" >PW : </label>
            <input type="password" name="pw" id="pw" class="form-control" placeholder="비밀번호를 입력하세요." required>
            <p class="askJoin">계정이 있으신가요? <a href="terms.us" class="joinLink">가입하기</a></p>
            <p class="askInfo">아이디 혹은 비밀번호를 잊으셨나요? <br/>
              <a href="findInfo.us">아이디 비밀번호 찾기</a>
            </p>
            <button type="submit" class="btn loginBtn">로그인</button>
          </form>
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
  
  <!-- JQuery -->
  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
  
  <script src="js/loginBtn.js"></script>
</body>
</html>
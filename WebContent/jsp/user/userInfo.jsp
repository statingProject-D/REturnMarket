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
    <link rel="stylesheet" href="css/join.css">
    <title>UserInfo</title>
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

            <div class="userInfo-container">
                <form action="userInfoModify.us" method="post" name="joinForm" class="joinForm"> <!-- onsubmit = submit할때 리턴이 true 일때만 실행 -->
                    <h2 class="form__title">회원 정보 변경</h2>
                    <table class="table table-hover">
                        <tr>
                            <td class="td-left">
                                <label for="pw">Password : </label>
                            </td>
                            <td class="td-right">
                                <input type="password" class="form-control pw" id="pw" name="pw" placeholder="password" required>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left">
                                <label for="re-pw">RePassword : </label>
                            </td>
                            <td class="td-right">
                                <input type="password" class="form-control rePw" id="rePw" name="rePw" placeholder="reconfirm password" required>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left">
                                <label for="name">Name : </label>
                            </td>
                            <td class="td-right">
                                <input type="text" class="form-control name" id="name" name="name" placeholder="name" required>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left">
                                <label for="phone">Phone Number : </label>
                            </td>
                            <td class="td-right">
                                <input type="tel" pattern="[0-9]{2~4}[0-9]{3,4}[0-9]{4}" class="form-control phone_num" id="phone" name="phone" placeholder="phone number (only number)" required><small>EX) 010.****.****, 02.5555.5555</small>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left">
                                <label for="email">E-Mail : </label>
                            </td>
                            <td class="td-right">
                                <input type="email" class="form-control email" id="email" name="email" placeholder="email" required>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-left">
                                <label for="addr">Address : </label>
                            </td>
                            <td class="td-right">
                                <div class="addr-inline__group">
                                <input type="text" id="sample4_postcode" class="addressNum" name="addrNum" placeholder="우편번호">
                                <input type="button" class="addressBtn" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
                                </div>
                                <input type="text" class="roadAddress" name="addr1" id="sample4_roadAddress" placeholder="도로명주소">
                                <input type="text" class="detailAddress" name="addr2" id="sample4_detailAddress" placeholder="상세주소">
                            </td>
                        </tr>
                    </table>
        
                    <div colspan="2" class="btn-box">
                        <!-- email에서 확인하는거 필요함 -->
                        <input type="submit" class="btn createId" value="수정">
                        <input type="reset" class="btn reset" value="초기화">
                    </div>
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

    <!-- kakao maps api -->
    <script src="/js/kakaoMaps.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <!-- bootstrip js link -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <!-- JQuery -->
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
</body>
</html>
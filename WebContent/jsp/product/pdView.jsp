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
  <link rel="stylesheet" href="css/pdview.css">
  <style>
    
  </style>
  <title>Product View</title>
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

        <div class="product-container">
          <div class="product-info">
            <div class="img-box">
            	<img src="upload/${pdBean.pdImg }"/>
            	<p>* 정보가 보이지 않으시면 새로고침을 해주세요</p>
            </div>

            <div class="user-box">
              <table class="table">
                <h2 class="pdName">${pdBean.pdName }</h2>
                <tbody>
                  <tr>
                    <td class="col-4">현재가 </td>
                    <td class="col-6" id="highestPrice"></td>
                    <td><div class="timer col-2" id="d-day"></div></td>
                  </tr>
                  <tr>
                    <td class="col-3">경매기간</td>
                    <td colspan="2" class="col-9">
                      <div id="startDay">${pdBean.auStartDay }</div> ~ <div id="endDay">${pdBean.auEndDay }</div>
                    </td>
                  </tr>
                  <tr>
                    <td>시작가</td>
                    <td colspan="2">${pdBean.auStartPrice }원</td>
                  </tr>
                  <tr>
                    <td>입찰단위</td>
                    <td colspan="2">${pdBean.auUnit }원</td>
                  </tr>
                  <tr>
                    <td>즉시구매가</td>
                	<td>${pdBean.auDirectPrice }원</td>
                   	<td><button onclick="location.href='directBuy.pd';" class="btn-x btn-danger">즉시구매요청</button></td>
                  </tr>
                  <tr>
                    <td>입찰자들</td>
                    <td colspan="2"><button class="btn-x btn-success bid-list" onclick="fadeIn()">입찰기록보기</button></td>
                  </tr>
                  <tr>
                    <td>판매자<br/>정보</td>
                    <td colspan="2">${userBean.email }</td>
                  </tr>
                </tbody>
              </table>

              <div class="gobtn-group">
                <button class="bid btn-x btn-primary" onclick="askBidBtn();">입찰하기</button>
                <button class="notice btn-x" onclick="location.href='jjimPd.pd';">찜하기</button>
                <c:if var="uploader" test="${pdBean.user_id == user_id }">
                	<button class="btn btn-success modiPd" onclick="location.href='modiPd.pd';">수정</button>
                	<button class="btn btn-info delPd" onclick="deleteBtn();">삭제</button>
                </c:if>
              </div>
            </div>
          </div>

          <div class="product-info__detail">
            <div class="product-info__detail-header">
              <span class="product-info__title">상품정보</span>
            </div>
            <textarea id="summernote">${pdBean.pdInfo }</textarea>
          </div>
        </div>
        
        <!-- 입찰한사람들 확인 -->
        <div class="directbuy-peoples bidTable">
          <div class="bid-container">
            <div class="bid-container__header">
              <span>입찰내역</span>
              <a href="#" class="backBtn closeBtn" onclick="fadeOut();"><i class="fas fa-times"></i></a>
            </div>

            <div class="bid-product__info">
              <span class="bid-product__name">* ${pdBean.pdName } *</span>
              <table class="table table-hover bid-table">
                  <tr>
                    <th class="col-1">번호</th>
                    <th class="col-4">입찰일시</th>
                    <th class="col-4">입찰자</th>
                    <th class="col-3">입찰금액</th>
                  </tr>
                  <c:forEach var="bid" items="${bidList}" varStatus="status">
                  <tr>
                    <td>${bid.bidNo}</td>
                    <td>${bid.nowtime }</td>
                    <td>${bid.user_id }</td>
                    <td id="highPrice">${bid.nowPrice }</td>
                  </tr>
                  </c:forEach>
              </table>
            </div>
          </div>
        </div>
      </main>

      <div class="fin-bid__container">
        <h1 class="fin-bid__title">경매가 종료되었습니다.</h1>
        <c:if test="${user_id eq pdBean.user_id }">
        	<button class="btn btn-danger delPd" onclick="location.href='delPd.pd?pdNum=${pdBean.pdNum}';">삭제</button>
        </c:if>
      </div>

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
  	var highPrice = document.querySelector("#highPrice").innerHTML;
  	var highestPrice = document.querySelector("#highestPrice");
  	
  	highestPrice.innerHTML = highPrice;
  </script>
  
  <!-- summernote js -->
  <script>
	  $('#summernote').summernote({
	      tabsize: 2,
	      height: 200
	      }); 
  </script>

  <script>
  	const askBidBtn = () => {
	  	var askBid = confirm("정말 입찰하시겠습니까?");
	  	
	  	if(!askBid) {
	  		alert('취소하셨습니다.');
	  	} else {
	  		alert('입찰하셨습니다.');
	  		location.href="bidPd.pd?pdNum=" +${pdBean.pdNum};
	  	}
  	}
  </script>

  <script>
	  const deleteBtn = () => {
		  var delBtn = confirm("정말 삭제하시겠습니까?");
		  
		  if(!delBtn) {
		    alert('취소하셨습니다.');
		  } else {
		    alert('성공적으로 삭제 하셨습니다.');
		    location.href="delPd.pd?pdNum=" +${pdBean.pdNum};
		  }
		}
  </script>

  <script src="js/fadeTable.js"></script>
  <script src="js/bidDDay.js"></script>
  <script src="js/askDelete.js"></script>
  <script src="js/askBidBtn.js"></script>
</body>
</html>
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
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
   <!--summernote css-->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet">
  <link rel="stylesheet" href="css/add-product.css">
  <title>Add Product</title>
</head>
<body>
  <div class="screen">
    <div class="screenForm">
      <main class="main">
        <div class="fix-header">
          <span class="fix-header__subtitle">상품수정</span>
          <a href="#" class="backBtn" onclick="history.back(-1);"><i class="fas fa-times"></i></a>
        </div>

        <form action="modiPdAction.pd" class="writeForm" method="post" enctype="multipart/form-data">
          <label for="pdName">* 상품명</label>
          <input type="text" class="form-control" id="pdName" name="pdName" value="${pdBean.pdName }" maxlength="30" required>
          
          <label for="pdCategory">* 상품 카테고리</label>
          <select class="form-control" id="pdCategory" name="pdCategory" required>
          	<option value="">--- 카테고리를 선택해 주세요 ---</option>
            <option value="전자제품">전자제품</option>
            <option value="주방용품">주방용품</option>
            <option value="잡화물품">잡화물품</option>
          </select>
          
          <label for="pdImg">* 상품 사진</label>
          <span>수정할 사진이 없는경우 사진 없음으로 설정됩니다.</span>
          <input multiple="multiple" type="file" class="form-control" id="pdImg" name="pdImg">
          
          <label for="pdCount">* 상품 수량</label>
          <input type="number" class="form-control" id="pdCount" name="pdCount" min="1" max="999" value="${pdBean.pdCount }" required>
          
          <label for="pdInfo">* 상품 설명</label>
          <textarea name="pdInfo" id="summernote" required>${pdBean.pdInfo }</textarea>
          
          <label for="product__group">* 상품 분류</label>
          <div id="product__group">
            <input type="radio" class="btn usedBtn" name="pdGroup" value="중고" checked><span class="radio-btn">중고</span>
            <input type="radio" class="btn newBtn" name="pdGroup" value="미개봉"><span class="radio-btn">미개봉</span>
          </div>

		<!-- 경매 -->
          <div class="auction-container form-horizontal">
            <h2 class="auction-title"><i>경매 설정</i></h2>
            <table class="table">
              <tr class="form-group__auction">
                <td class="col-3"><label for="auStartPrice">* 시작가격</label></td>
                <td class="col-8"><input type="number" class="form-control" min="0" id="startPrice" class="start__cost" name="auStartPrice" value="${pdBean.auStartPrice }"></td>
                <td class="col-1">원</td>
              </tr>
              
              <tr class="form-group__auction">
                <td class="col-3"><label for="auUnit">* 입찰단위</label></td>
                <td colspan="2" class="col-9">
                  <select name="auUnit" id="auUnit" class="form-control">
                    <option value="">-입찰단위를 선택하세요-</option>
                    <option value="200">200</option>
                    <option value="1000">1,000</option>
                    <option value="2000">2,000</option>
                    <option value="5000">5,000</option>
                    <option value="10000">10,000</option>
                  </select>
                </td>
              </tr>

              <tr class="form-group__auction">
                <td class="col-3"><label for="auPeriod">* 경매마감(일)</label></td>
                <td colspan="2" class="col-9 day-bar">
                  <input type="datetime-local" class="form-control auction-period" min="" id="auEndDay" name="auEndDay" required>
                </td>
              </tr>

              <tr class="form-group__auction">
                <td class="col-3"><label for="auDirectPrice">* 즉시 구매 가격</label></td>
                <td class="col-8"><input type="number" class="form-control" min="0" id="start-cost" class="auDirectPrice" name="auDirectPrice" value="${pdBean.auDirectPrice }" required></td>
                <td class="col-1">원</td>
              </tr>
            </table>
            <span class="info__subtitle">- 경매 기준마감 시간은 익일 오전 0시 입니다. -</span><br />
          </div>

          <div class="form-inline addBtn__group">
            <div class="form-group__bt">
              <button type="reset" class="btn resetBtn">다시 작성</button>
            </div>
            <div class="form-group__btn">
              <button type="submit" class="btn addProductBtn">등록 완료</button>
            </div>
          </div>
        </form>
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
  <!-- summernote js -->
  <script>
	  $('#summernote').summernote({
	      placeholder: '상품에 대한 자세한 설명을 적어주세요!',
	      tabsize: 2,
	      height: 200
	      }); 
  </script>
  
  <script src="js/addPdDateLimit.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<style>
	.note-toolbar {
		display: none;
	}
</style>
<title>Product View</title>
</head>
<body>
	<h1>상품정보</h1>
	<form action="modiPd.ad?pdNum=${vo.pdNum }" method="post" class="form-control" enctype="multipart/form-data">
	<div class="btn-group">
		<input type="submit" class="btn btn-success" value="수정본 저장">
		<input type="reset" class="btn btn-secondary" value="초기화">
		<a href="productInfo.ad" class="btn btn-primary">홈으로</a>
		<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#askDelete">삭제</button>
		
		<!-- modal을 사용한 취소 물어보기 -->
		<div class="modal fade" id="askDelete" tabindex="-1" role="dialog" aria-labelledby="askDeleteLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="askDeleteLabel">게시물 삭제</h5>
						<button type="button" class="close btn" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true"><i class="fas fa-times"></i></span>
						</button>
					</div>
					<div class="modal-body">정말 게시물을 삭제하시겠습니까?</div>
					<div class="modal-footer">
						<a href="delPd.ad?pdNum=${vo.pdNum }" class="btn btn-primary">삭제하기</a>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table class="table">
		<tr>
			<td>제목</td>
			<td colspan="3"><input type="text" name="pdName" class="form-control" value="${vo.pdName }"></td>
		</tr>
		<tr>
			<td>품번</td>
			<td><input type="text" name="pdNum" class="form-control" value="${vo.pdNum }" readonly></td>
			<td>작성자</td>
			<td><input type="text" name="user_id" class="form-control" value="${vo.user_id }" readonly></td>
		</tr>
		<tr>
			<td>상품 분류</td>
			<td>
				<select name="pdGroup" id="pdGroup" class="form-control">
                  <option value="${vo.pdGroup }">기존 : ${vo.pdGroup }</option>
                  <option value="중고">중고</option>
                  <option value="미개봉">미개봉</option>
                </select>
            </td>
			<td>카테고리</td>
			<td>
				<select name="pdCategory" id="pdCategory" class="form-control">
                  <option value="${vo.pdCategory }">기존 : ${vo.pdCategory }</option>
                  <option value="전자제품">전자제품</option>
            	  <option value="주방용품">주방용품</option>
           		  <option value="잡화물품">잡화물품</option>
                </select>
            </td>
		</tr>
		<tr>
			<td>경매 시작일</td>
			<td><input type="text" name="auStartDay" class="form-control" readonly value="${vo.auStartDay }"></td>
			<td>경매 마감일</td>
			<td><input type="text" class="form-control" name="auEndDay" value="${vo.auEndDay }"></td>
		</tr>
		<tr>
			<td>상품 시작 가격</td>
			<td><input type="text" name="auStartPrice" class="form-control" value="${vo.auStartPrice }"></td>
			<td>입찰 단위</td>
			<td>
				<select name="auUnit" id="auUnit" class="form-control">
                  <option value="${vo.auUnit }">${vo.auUnit }원</option>
                  <option value="200">200원</option>
                  <option value="1000">1,000원</option>
                  <option value="2000">2,000원</option>
                  <option value="5000">5,000원</option>
                  <option value="10000">10,000원</option>
                </select>
           	</td>
		</tr>
		<tr>
			<td>상품 수량</td>
			<td><input type="number" class="form-control" name="pdCount" value="${vo.pdCount }"></td>
			<td>즉시구매가</td>
			<td><input type="text" name="auDirectPrice" class="form-control" value="${vo.auDirectPrice }"></td>
		</tr>
		<tr>
			<td>상품 이미지</td>
			<td><span>기존 이미지 :</span><input type="text" class="form-control" name="defaultPdImg" value="${vo.pdImg }"></td>
			<td colspan="2"><span>변경할 이미지 : </span><input multiple="multiple" type="file" class="form-control" id="pdImg" name="pdImg"></td>
		</tr>
		<tr>
			<td>상품 정보</td>
			<td colspan="3">
				<textarea id="summernote" name="pdInfo">${vo.pdInfo }</textarea>
			</td>
		</tr>
	</table>
	</form>
	
	<!-- summernote js -->
	<script>
	  $('#summernote').summernote({
	      tabsize: 2,
	      height: 300
	      }); 
 	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
<style>
	.note-toolbar {
		display: none;
	}
	.btn-group {
		width: 100%;
		margin: 15px 0;
	}
</style>
<title>Write Notice</title>
</head>
<body>
	<form action="writeNoticeAction.ad" method="post">
		<table class="table">
			<tr>
				<td class="col-2">작성자</td>
				<td class="col-10"><input type="text" name="user_id" class="form-control" value="${id}" readonly></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" class="form-control" placeholder="제목을 적어 주세요"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="summernote" name="content"></textarea></td>
			</tr>
		</table>
		<div class="btn-group">
			<input type="submit" class="btn btn-primary" value="완료">
			<input type="reset" class="btn btn-secondary" value="다시작성">
		</div>
	</form>
	
	<!-- summernote js -->
	  <script>
	  $('#summernote').summernote({
	      tabsize: 2,
	      height: 600
	      }); 
 	 </script>
</body>
</html>
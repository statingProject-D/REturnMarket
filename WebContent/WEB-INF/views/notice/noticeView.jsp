<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Notice View</title>
</head>
<body>
	<form action="modiNotice.ad?no=${vo.no }" method="post" class="form-control">
	<table class="table">
		<tr>
			<td class="col-1">글번호</td>
			<td class="col-1">${vo.no }</td>
			<td class="col-2">관리자명</td>
			<td class="col-8"><input type="text" name="user_id" class="form-control" value="${vo.user_id }"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="5"><input type="text" class="form-control" name="title" value="${vo.title }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="5"><textarea id="summernote" name="content">${vo.content }</textarea></td>
		</tr>
	</table>
		<div class="btn-group">
			<input type="submit" class="btn btn-success" value="수정">
			<input type="reset" class="btn btn-secondary" value="초기화">
			<a href="notice.ad" class="btn btn-primary">뒤로</a>
		</div>
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
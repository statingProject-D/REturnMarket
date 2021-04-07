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
<title>header</title>
<link rel="stylesheet" href="css/layout/header.css">
</head>
<body>
    <header>
		<img src="img/logo.png" class="logo__img" width="140px">
		<ul class="header-list">
            <li>
                <a href="approval.ad" class="col-3 taglink">관리자 가입 승인</a>
            </li>
            <li>
                <a href="userInfo.ad" class="col-3 taglink">이용자 정보</a>
            </li>
            <li>
                <a href="productInfo.ad" class="col-3 taglink">게시물 관리</a>
            </li>
            <li>
                <a href="notice.ad" class="col-3 taglink">공지사항 관리</a>
            </li>
		</ul>
		<div class="login-info">
            <h5><font>${id}</font>님 반갑습니다.</h5>
            <a href="logout.ad" class="btn btn-primary logout">로그아웃</a>
		</div>
	</header>
</body>
</html>
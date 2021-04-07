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
<link rel="stylesheet" href="css/admin/join.css">
<title>Join</title>
</head>
<body>
	<div class="join">
        <form action="joinAction.ad" method="post" class="form-control join-form">
            <sapn class="row">ID : </sapn> <input type="text" class="form-control id-input" name="id" placeholder="id는 최소 6자리 입니다." pattern="[A-Za-z0-9]{6,20}" required>
            <sapn class="row">PASSWORD : </sapn><input type="password" class="form-control pw" name="pw" placeholder="영문,숫자,특수문자 포함 8자리 이상" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$" required>
            <sapn class="row">NAME : </sapn><input type="text" class="form-control name" name="name" placeholder="NAME" required>
            <sapn class="row">PHONE : </sapn><input type="tel" name="phone" pattern="[0-9]{2~4}[0-9]{3,4}[0-9]{4}" class="form-control" placeholder="EX) 010.****.****, 02.5555.5555" required>
            <sapn class="row">E-MAIL : </sapn><input type="email" class="form-control email" name="email" placeholder="email" required>
            <div class="btn-group">
                <input type="submit" class="btn btn-primary" value="가입">
                <input type="reset" class="btn btn-success" value="초기화">
                <a href="login.ad" class="btn btn-info">HOME</a>
            </div>
        </form>
    </div>
    
    <!-- bootstrip js link -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
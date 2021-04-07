<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify UserInfo</title>
<style>
	.container {
		margin: 20px;
		padding: 20px;
	}
	.user_id {
		color: #0080ff;
	}
	.inline{
		display: flex;
	}
	.btn-group {
		margin: 15px 0;
		width: 100%;
		display: flex;
		justify-content: center;
	}
</style>
</head>
<body>
	<form action="modiUserAction.ad?user_id=${vo.user_id}" method="post" class="container form-control">
		<h2><span class="user_id">${vo.user_id}</span>님 정보 변경</h2>
		<span>이름 : </span>
		<input type="text" class="form-control" name="name" value="${vo.name }"><br />
		<span>전화번호 : </span>
		<input type="tel" class="form-control" name="phone" value="${vo.phone }"><br />
		<span>이메일 : </span>
		<input type="email" class="form-control" name="email" value="${vo.email}"><br />
		<span>등급 : </span>
		<input type="text" class="form-control" name="grade" value="${vo.grade }"><br />
		<span>주소 : </span>
		<div class="inline">
			<input type="text" class="form-control" name="addrNum" value="${vo.addrNum }">
			<input type="text" class="form-control" name="addr1" value="${vo.addr1 }">
			<input type="text" class="form-control" name="addr2" value="${vo.addr2 }">
		</div><br/>
		<span>이메일 체크 여부 : </span>
		<input type="text" class="form-control" name="emailChk" value="${vo.emailChk}"><br />
		<div class="btn-group">
			<input type="submit" class="btn btn-success" value="변경">
			<input type="reset" class="btn btn-secondary" value="초기화">
			<a href="userInfo.ad" class="btn btn-primary">Home</a>
		</div>
	</form>
	
	<form action="changePw.ad?user_id=${vo.user_id}" method="post" class="container form-control">
		<h2><span class="user_id">${vo.user_id}</span>님 비밀번호 변경</h2>
		<input type="password" class="form-control" name="pw" placeholder="변경할 비밀번호를 입력하세요."><br/>
		<div class="btn-group">
			<input type="submit" class="btn btn-success" value="변경">
			<input type="reset" class="btn btn-secondary" value="초기화">
			<a href="userInfo.ad" class="btn btn-primary">Home</a>
		</div>
	</form>
</body>
</html>
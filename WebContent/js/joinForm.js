const userIdChk = () => {
	var chkUserId = false;
	
	var tfUserId = $("#user_id").val();
	console.log(tfUserId);
	
	$.ajax(
		{
			type : 'post',
			url : '/joinForm.us' +tfUserId
		}
	).done(
		function(result) {
			console.log(result);
			if(result == 1) {
				alert('아이디가 중복되었습니다.');
			} else if(result == 0) {
				alert('사용하실 수 있는 아이디 입니다.');
				chkUserId = true;
			} else if(result == 2) {
				alert('아이디를 입력해주세요.');
			} else {
				console.log('develop : 서버 오류');
			}
		}
	).fail(
		function (error) {
			console.log(error);
		}
	);
}
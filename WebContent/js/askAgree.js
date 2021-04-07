const agree = () => {
	  var chkbox = document.querySelector(".checkbox");

	  if(!chkbox.checked) {
	    alert("약관에 동의해 주세요");
	  } else {
	    location.href="joinForm.us";
	  }
}

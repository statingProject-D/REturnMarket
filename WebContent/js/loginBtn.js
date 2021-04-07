const loginForm = document.querySelector(".loginForm");
const id = loginForm.querySelector("#id"),
  pw = loginForm.querySelector("#pw");
const loginBtn = document.querySelector(".loginBtn");

$(id).on("input", checkLogin);
$(pw).on("input", checkLogin);

function checkLogin() {
  let v_id = $(id).val();
  let v_pw = $(pw).val();

  if(v_id !== '' && v_pw !== '') {
    $(loginBtn).css('background-color', "#0080ff").css('color', 'white');
  } else {
    $(loginBtn).css('background-color', "white").css('color', 'black');
  }
};
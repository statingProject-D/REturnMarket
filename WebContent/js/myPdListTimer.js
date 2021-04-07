let timer = document.querySelector("#timer");
let endDay = document.querySelector("#endDay").innerText;
let dealBtn = document.querySelector(".dealBtn");

function getTime() {
  const end = new Date(endDay).getTime();
  const start = new Date().getTime();
  const gap = end - start;
  var d = Math.floor(gap / (1000 * 60 * 60 * 24));
  var h = Math.floor((gap % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var m = Math.floor((gap % (1000 * 60 * 60)) / (1000 * 60));
  var s = Math.floor((gap % (1000 * 60)) / 1000);
  if (gap <= 0) {
    timer.innerText = "경매 시간이 마감되었습니다.";
    dealBtn.classList.add("showBtn");
  } else {
    timer.innerText = `${d}일 ${h}시간 ${m}분 ${s}초 남았습니다.`;
  }
}

function init() {
  getTime();
  setInterval(getTime, 1000);
}

init();
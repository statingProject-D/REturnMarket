let dDay = document.querySelector("#d-day");
let endDay = document.querySelector("#endDay").innerText;
const gobtnGroup = document.querySelector(".gobtn-group"),
  finBidContaier = document.querySelector(".fin-bid__container");


function getTime() {
  const target = new Date(endDay).getTime();
  const today = new Date();
  const gap = target - today;
  var d = Math.floor(gap / (1000 * 60 * 60 * 24));
  var h = Math.floor((gap % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var m = Math.floor((gap % (1000 * 60 * 60)) / (1000 * 60));
  var s = Math.floor((gap % (1000 * 60)) / 1000);
  if (gap <= 0) {
    dDay.innerHTML = "경매 시간이 마감되었습니다."
    gobtnGroup.classList.add("hiddenBtn");
    finBidContaier.classList.add("fin-bid__show");
  } else {
    dDay.innerText = `${d}일 ${h}시간 ${m}분 ${s}초 남았습니다.`;
  }
}

function init() {
  getTime();
  setInterval(getTime, 1000);
}

init();
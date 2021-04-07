// date 최소 기준 시간을 오늘로 설정
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth() + 1; // january is 0!
var yyyy = today.getFullYear();
if(dd < 10) {
  dd = '0'+dd
}
if(mm < 10) {
  mm = '0'+mm
}
today = yyyy +'-' +mm +'-' +dd+"T00:00";

document.getElementById("auEndDay").setAttribute("min", today);
const addClassBtn = document.querySelector(".bid-list");
const removeClassBtn = document.querySelector(".closeBtn");
const bidTable = document.querySelector(".bidTable");

const addClassFade = () => {
  bidTable.classList.add("fadeinTable");
}

const removeClassFade = () => {
  bidTable.classList.remove("fadeinTable");
}

function fadeIn() {
  addClassBtn.addEventListener("click", addClassFade);
}

function fadeOut() {
  removeClassBtn.addEventListener("click", removeClassFade);
}
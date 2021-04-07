$(function(){
  var lastScroll = 0;
  $(window).scroll(function(e){
    var scroll = $(this).scrollTop();
    if (scroll > 154){
      //이벤트를 적용시킬 스크롤 높이
      $("#fadeout").removeClass("fadeout");
    } else if (scroll < 154) {
      $("#fadeout").addClass("fadeout");
    }
    lastScroll = scroll;
  });
});
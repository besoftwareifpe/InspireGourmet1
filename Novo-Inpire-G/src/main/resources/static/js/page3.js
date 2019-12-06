$(document).ready(function(){
    $('.post-module').hover(function() {
        $(this).find('.description').stop().animate({
          height: "toggle",
          opacity: "toggle"
        }, 300);
      });


      var logID = 'log',
      log = $('<div id="'+logID+'"></div>');
      $('body').append(log);
      $('[type*="radio"]').change(function () {
        var me = $(this);
        log.html(me.attr('value'));
      });
});


// 

$(document).ready(function() {
    let backtotop = $('.back-to-top');
    const toggleBacktotop = () => {
        if ($(window).scrollTop() > 100) {
            backtotop.addClass('active');
        } else {
            backtotop.removeClass('active');
        }
    };
    $(window).on('load', toggleBacktotop);
    $(window).on('scroll', toggleBacktotop);
});
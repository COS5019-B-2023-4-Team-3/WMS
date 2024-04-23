window.onload = function() {
    // back_to_top.js
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

    // dropdown.js
    // Hide dropdown menus by default
    $('.dropdown-menu').hide();

    // Delegated event handling for dropdown toggle
    $(document).on('click', '.nav-link.nav-icon', function(e) {
        e.preventDefault();
        var dropdownMenuClass = $(this).attr('data-dropdown-menu-class');
        // Hide all dropdown menus except the one that corresponds to the clicked icon
        $('.dropdown-menu').not('.' + dropdownMenuClass).hide();
        // Toggle visibility of the dropdown menu
        $(this).closest('.dropdown').find('.dropdown-menu').toggle();
    });

    // Event handling for profile dropdown toggle
    $(document).on('click', '.nav-link.nav-profile', function(e) {
        e.preventDefault();
        var dropdownMenuClass = $(this).attr('data-dropdown-menu-class');
        // Hide all dropdown menus except the one that corresponds to the clicked icon
        $('.dropdown-menu').not('.' + dropdownMenuClass).hide();
        // Toggle visibility of the profile dropdown menu
        $(this).closest('.dropdown').find('.dropdown-menu').toggle();
    });

    // Close dropdown when clicking outside of it
    $(document).click(function(e) {
        if (!$(e.target).closest('.dropdown').length) {
            $('.dropdown-menu').hide();
        }
    });

    // sidebar_toggle.js
    $('.toggle-sidebar-btn').click(function() {
        $('body').toggleClass('toggle-sidebar');
    });

    // pageshow.js
    // checks if the pageshow event was fired due to a page coming out of
    // the browser's page cache (also known as back-forward cache, or bfcache).
    // If it was, it forces a page reload.
    window.addEventListener('pageshow', function (event) {
        if (event.persisted) {
            window.location.reload();
        }
    });
};
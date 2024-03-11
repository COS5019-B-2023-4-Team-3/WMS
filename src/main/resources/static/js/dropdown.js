$(document).ready(function() {
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
});

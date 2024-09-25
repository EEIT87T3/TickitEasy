$(document).ready(function() {
    $('#sidebarToggle').click(function() {
        $('.sidebar').toggleClass('sidebar-closed');
        $('.content-with-sidebar').toggleClass('content-full');
        $(this).toggleClass('button-shifted');
    });

    function initSidebar() {
        if (window.innerWidth >= 1024) {
            $('.sidebar').removeClass('sidebar-closed');
            $('.content-with-sidebar').removeClass('content-full');
            $('#sidebarToggle').removeClass('button-shifted');
        } else {
            $('.sidebar').addClass('sidebar-closed');
            $('.content-with-sidebar').addClass('content-full');
            $('#sidebarToggle').addClass('button-shifted');
        }
    }

    initSidebar();
    $(window).resize(initSidebar);
});
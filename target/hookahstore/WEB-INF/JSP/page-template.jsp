<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shoppy Store - Premium Multipurpose HTML5/CSS3 Theme</title>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="msapplication-TileImage" content="icons/cropped-favicon-270x270.png"/>

    <!-- FAVICONS -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/static/icons/apple-touch-icon-144-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/static/icons/apple-touch-icon-114-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/static/icons/apple-touch-icon-72-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" href="/static/icons/apple-touch-icon-57-precomposed.png"/>
    <link rel="shortcut icon" href="/static/icons/favicon.png"/>

    <!-- GOOGLE WEB FONTS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:100,400,500,700,900&ver=1.0.0"
          type="text/css" media="all"/>

    <!-- BOOTSTRAP 3.3.7 CSS -->
     <link rel="stylesheet" href="/static/css/theme/bootstrap.min.css"/>

    <!-- OPEN LIBS CSS -->
    <link rel="stylesheet" href="/static/css/theme/font-awesome.min.css"/>
    <link rel="stylesheet" href="/static/css/owl-carousel/owl.carousel.min.css"/>

    <link rel="stylesheet" href="/static/css/js_composer/js_composer.min.css"/>

    <!-- YT CSS -->
    <link rel="stylesheet" href="/static/css/colorbox/colorbox.css"/>
    <link rel="stylesheet" href="/static/css/sw_core/jquery.fancybox.css"/>
    <link rel="stylesheet" href="/static/css/sw_woocommerce/slider.css"/>
    <link rel="stylesheet" href="/static/css/woocommerce/woocommerce-layout.css"/>
    <link rel="stylesheet" href="/static/css/woocommerce/woocommerce-smallscreen.css"/>
    <link rel="stylesheet" href="/static/css/woocommerce/woocommerce.css"/>
    <link rel="stylesheet" href="/static/css/theme/wishtlist.css"/>
    <link rel="stylesheet" href="/static/css/theme/app-blue.min.css" id="theme"/>
    <link rel="stylesheet" href="/static/css/theme/app-responsive.min.css"/>
    <link rel="stylesheet" href="/static/css/theme/flexslider.min.css"/>
    <link rel="stylesheet" href="/static/css/theme/custom-blue.min.css"/>
    <link rel="stylesheet" href="/static/css/theme/home-style-1.min.css"/>

    <link rel="stylesheet" href="/static/css/app.css"/>
</head>

<body id="body_wrapper" class="archive woocommerce woocommerce-page vc_responsive">
<!-- Preloading Screen -->
<div class="ip-header">
    <h1 class="ip-logo">
        <a>
            <img src="/static/images/theme_logo.png" alt="sw shoppy"/>
        </a>
    </h1>
    <div class="ip-loader">
        <svg class="ip-inner" width="60px" height="60px" viewBox="0 0 80 80">
            <path class="ip-loader-circlebg"
                  d="M40,10C57.351,10,71,23.649,71,40.5S57.351,71,40.5,71 S10,57.351,10,40.5S23.649,10,40.5,10z"></path>
            <path id="ip-loader-circle" class="ip-loader-circle"
                  d="M40,10C57.351,10,71,23.649,71,40.5S57.351,71,40.5,71 S10,57.351,10,40.5S23.649,10,40.5,10z"
                  style="stroke-dashoffset: 192.617; stroke-dasharray: 192.617;"></path>
        </svg>
    </div>
</div>
<!-- End Preloading Screen -->

<div class="body-wrapper theme-clearfix">
    <header>
        <jsp:include page="fragment/header.jsp"/>
    </header>
    <main>
        <jsp:include page="${currentPage}"/>
    </main>
    <footer>
        <jsp:include page="fragment/footer.jsp"/>
    </footer>
</div>
<a id="ya-totop" href="#" style="display: none;"></a>


<script src="/static/js/jquery.js"/>
<script type="text/javascript" src="/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery-migrate-1.4.1.min.js"></script>
<script type="text/javascript" src="/static/js/modernizr-2.6.2.min.js"></script>

<!-- BOOTSTRAP 3.3.7 JS -->
<script type="text/javascript" src="/static/js/bootstrap/bootstrap.min.js"></script>

<!-- OPEN LIBS JS -->
<script type="text/javascript" src="/static/js/owl-carousel/owl.carousel.min.js"></script>
<script type="text/javascript" src="/static/js/js.cookie.min.js"></script>

<!-- PLUGINS JS -->
<script type="text/javascript" src="/static/js/sw_woocommerce/category-ajax.min.js"></script>
<script type="text/javascript" src="/static/js/sw_woocommerce/slick.min.js"></script>
<script type="text/javascript" src="/static/js/colorbox/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="/static/js/sw_core/jquery.fancybox.pack.js"></script>


<!-- THEME JS -->
<script src="/static/js/main.js"></script>
<script src="/static/js/megamenu.min.js"></script>
<script src="/static/js/pathLoader.min.js"></script>

<!-- CUSTOM JS-->
<script type="text/javascript">
    (function ($) {
        $('.view-list').on('click', function () {
            $('.view-grid').removeClass('sel');
            $('.view-list').addClass('sel');
            jQuery("ul.products-loop").fadeOut(300, function () {
                jQuery(this).addClass("list").fadeIn(300).removeClass('grid')
            })
        });
        $('.view-grid').on('click', function () {
            $('.view-list').removeClass('sel');
            $('.view-grid').addClass('sel');
            $("ul.products-loop").fadeOut(300, function () {
                $(this).removeClass("list").fadeIn(300).addClass('grid')
            })
        });
        jQuery('.phone-icon-search').on('click', function () {
            jQuery('.sm-serachbox-pro').toggle("slide")
        });
        var sticky_navigation_offset = $(".yt-header-middle").offset();
        var sticky_navigation_offset_top = sticky_navigation_offset.top;
        var sticky_navigation = function () {
            var scroll_top = $(window).scrollTop();
            if (scroll_top > sticky_navigation_offset_top) {
                $(".yt-header-middle").addClass("sticky-menu");
                $(".yt-header-middle").css({"position": "fixed", "top": 0, "left": 0, "right": 0, "z-index": 800})
            } else {
                $(".yt-header-middle").removeClass("sticky-menu");
                $(".yt-header-middle").css({"position": "relative", "z-index": 30})
            }
        };
        sticky_navigation();
        $(window).scroll(function () {
            sticky_navigation()
        });
        $(document).ready(function () {
            $(".show-dropdown").each(function () {
                $(this).on("click", function () {
                    $(this).toggleClass("show");
                    $(this).parent().find("> ul").toggle(300)
                })
            })
        })
    }(jQuery))
</script>
</body>
</html>
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


    <link rel="stylesheet" href="/static/css/js_composer/js_composer.min.css"/>

    <!-- YT CSS -->
    <link rel="stylesheet" href="/static/css/colorbox/colorbox.css"/>
    <link rel="stylesheet" href="/static/css/sw_core/jquery.fancybox.css"/>
    <link rel="stylesheet" href="/static/css/sw_woocommerce/slider.css"/>
    <link rel="stylesheet" href="/static/css/woocommerce/woocommerce-layout.css"/>
    <link rel="stylesheet" href="/static/css/woocommerce/woocommerce-smallscreen.css"/>
    <link rel="stylesheet" href="/static/css/woocommerce/woocommerce.css"/>
    <link rel="stylesheet" href="/static/css/theme/app-blue.min.css" id="theme"/>
    <link rel="stylesheet" href="/static/css/theme/app-responsive.min.css"/>
    <link rel="stylesheet" href="/static/css/theme/flexslider.min.css"/>
    <link rel="stylesheet" href="/static/css/theme/custom-blue.min.css"/>
    <link rel="stylesheet" href="/static/css/theme/home-style-1.min.css"/>

    <link rel="stylesheet" href="/static/css/app.css"/>
</head>

<body id="body_wrapper" class="archive woocommerce woocommerce-page vc_responsive">


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


<!-- THEME JS -->
<script src="/static/js/main.js"></script>
<script src="/static/js/megamenu.min.js"></script>


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
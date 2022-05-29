(function ($) {
    "use strict";
    $(document).on('click', '.add_to_cart_button', function (ev) {
        ev.preventDefault();
        var $this = $(this);
        $this.removeClass('added').addClass('loading');
        window.setTimeout(function () {
            $this.removeClass('loading').addClass('added')
        }, 1000);
        return false;
    });

    $('.vertical-megamenu .dropdown').on('hover', function (e) {
        $(this).find('> ul.dropdown-menu').toggle(300)
    });

    $('.ver-megamenu-header').on('hover', function (e) {
        var verticalMenu = $(this).find('> ul.vertical-megamenu');
        if (!verticalMenu.is(':visible')) {
            verticalMenu.slideToggle(300);
            verticalMenu.addClass('showed')
        } else {
            if (verticalMenu.hasClass('showed')) {
                verticalMenu.slideToggle(300);
                verticalMenu.removeClass('showed')
            }
        }
    });

    $("#ya-totop").hide();
    var wh = $(window).height();
    var whtml = $(document).height();
    $(window).scroll(function () {
        if ($(this).scrollTop() > whtml / 10) {
            $('#ya-totop').fadeIn()
        } else {
            $('#ya-totop').fadeOut()
        }
    });
    $('#ya-totop').on('click', function () {
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return !1
    });


    try {
        jQuery('.phone-icon-search').on('click', function () {
            jQuery('.sm-searchbox-pro').toggle("slide");
        });
    } catch (e) {
    }

    var postRequest = function(url){
        var form = '<form id="postRequestForm" action="'+url+'" method="post"></form>';
        $('body').append(form);
        $('#postRequestForm').submit();
    };

    var pagination = function(){
        var pageNumber = parseInt($(this).text())-1;
        //удаление параметра страницы: если он первый то ?page=,а если последующий то &page=
        var query=location.search.replace(/.page=\d+/,"");
        //создание урла для редиректа
        var url = (query=="" | query==null)?location.pathname+'?page='+pageNumber:location.pathname+query+'&page='+pageNumber;
        location.href=url;
    }
    var showPopup = function () {
        var idProduct = $(this).parent().parent().attr('id');
        var product = $(this).parent().parent();
        $('#addProductPopup').attr('data-id-product', idProduct);
        $('#addProductPopup .product-image').attr('src', product.find('.products-thumb img').attr('src'));
        $('#addProductPopup .product_title').text(product.find('.name').text());
        $('#addProductPopup .product-description p').text(product.find('.desc.std p').text());
        var price = product.find('.item-price .woocommerce-Price-amount.amount').text();
        $('#addProductPopup .quantity .count').val(1);
        $('#addProductPopup .price .woocommerce-Price-amount.amount').text(price);
        $('#addProductPopup .category').text(product.find('.category').text());
        $('#addProductPopup').modal({
            show: true
        });
    }


    $('#cpanel-reset').on('click', function (e) {
        var defaultTheme = $('#colorPanel').find('[data-theme-default="true"]');
        if (defaultTheme != undefined) {
            var defaultColor = $(defaultTheme).data('theme');
            var theme = "css/theme/app-" + defaultColor + ".min.css";
            $("#theme").attr("href", theme);
            Cookies.remove('theme');
            $('#colorPanel').find('.ya-radio-img-selected').removeClass('ya-radio-img-selected');
            $('img[data-theme=' + defaultColor + ']').parents('label').addClass('ya-radio-img-selected')
        }
        return false;
    });

    var deleteProduct = function () {
        $('form.shopping-cart').submit(function (e) {
            e.preventDefault();
        });
        var product = $(this).parent().parent();
        var idProduct = parseInt(product.attr('id'));
        var count = product.find('.input-text.qty.text').val();
        console.log(count);
        $.ajax({
            url: "/ajax/json/product/delete",
            method: 'post',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                console.log(data);
                if (data.totalCount == 0) {
                    window.location.href = '/products';
                } else {
                    var tr = $('[name="update_cart"][data-id-product="product' + idProduct + '"]').parent().parent();
                    tr.remove();
                    product.remove();
                    $('.order-total .woocommerce-Price-amount.amount').text(data.totalCost);
                    $('.cart-contents .minicart-number').text(data.totalCount);
                    $('.cart-contents .amount').text(data.totalCost);
                }
            },
            error: function (xhr) {
                if (xhr.status == 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        });

    }


    var updateCart = function () {
        $('form.shopping-cart').submit(function (e) {
            e.preventDefault();
        });
        var id = $(this).attr('data-id-product').replace("product", "");
        var product = $('.cart_item[id=' + id + ']');
        var count = product.find('.quantity input').val();
        var priceStr = product.find('.product-price span').text();
        var price = parseInt(priceStr);
        $.ajax({
            url: "/ajax/json/shopping-cart/update",
            method: 'post',
            data: {
                idProduct: id,
                count: count
            },
            success: function (data) {
                if (parseInt(count) === 0) {
                    var tr = $('[name="update_cart"][data-id-product="product' + id + '"]').parent().parent();
                    tr.remove();
                    product.remove();
                } else {
                    product.find('.product-subtotal span').text(data.subTotalMap[id] + " ₽");
                    product.find('.quantity input').val(data.countMap[id]);
                }
                $('.order-total .woocommerce-Price-amount.amount').text(data.totalCost + " ₽");
                $('.cart-contents .minicart-number').text(data.totalCount);
                $('.cart-contents .amount').text(data.totalCost);
            },
            error: function (xhr) {
                if (xhr.status == 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        });
    };

    var changeProductCount = function () {
        var idProduct = $('#addProductPopup').attr('data-id-product');
        var product = $('.products-entry.clearfix[id=' + idProduct + ']');
        var priceStr = product.find('.item-price .woocommerce-Price-amount.amount').text();
        var price = parseInt(priceStr)
        var count = parseInt($('#addProductPopup .quantity .count').val());
        var min = parseInt($('#addProductPopup .quantity .count').attr('min'));
        var max = parseInt($('#addProductPopup .quantity .count').attr('max'));
        if (count >= min && count <= max) {
            var cost = price * count;
            $('#addProductPopup .woocommerce-Price-amount.amount').text(cost + " ₽");
        } else {
            $('#addProductPopup .woocommerce-Price-amount.amount').text(priceStr);
            $('#addProductPopup .quantity .count').val(1);
        }
    }

    var addProductToCartFromPage = function () {
        var idProduct = $(this).attr('id');
        var count = 1;
        $.ajax({
            url: "/ajax/json/product/add",
            method: 'post',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                console.log(data);
                $('.cart-contents .minicart-number').text(data.totalCount);
                $('.cart-contents .amount').text(data.totalCost);
            },
            error: function (xhr) {
                if (xhr.status == 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        });
    }

    var addProductFromQuickView = function () {
        $('#addProductPopup form').submit(function (e) {
            e.preventDefault();
        });
        var idProduct = $('#addProductPopup').attr('data-id-product');
        var count = $('#addProductPopup .quantity .count').val();
        $.ajax({
            url: "/ajax/json/product/add",
            method: 'post',
            data: {
                idProduct: idProduct,
                count: count
            },
            success: function (data) {
                console.log(data.totalCount+" - " + data.totalCost);
                $('.cart-contents .minicart-number').text(data.totalCount);
                $('.cart-contents .amount').text(data.totalCost);
                $('#addProductPopup').modal('hide');
            },
            error: function (xhr) {
                if (xhr.status == 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        });
    }

    var loadMoreOrders = function(){
        $.ajax({
            url:'/ajax/html/orders/more',
            success: function(html){
                $(document).append(html);
            }
        })    }


    var initBtn = function () {
        $('a[title="Quick View Product"]').click(showPopup);
        $('input[name="update_cart"]').click(updateCart);
        $('#addProductPopup .count').change(changeProductCount);
        $('a.add_to_cart_button').click(addProductToCartFromPage);
        $('.single_add_to_cart_button').click(addProductFromQuickView);
        $('.product-remove .remove').click(deleteProduct);
        $('.post-request').click(function(){
            postRequest($(this).attr('data-url'));
        });
        $('a.page-numbers').click(pagination);
        $('a[href="#cpanel-form"]').on('click', function (e) {
            e.preventDefault();
            var parent = $('#cpanel-form'),
                right = parent.css('right'),
                width = parent.width();
            if (parseFloat(right) < -10) {
                parent.animate({
                    right: '0px',
                }, "slow")
            } else {
                parent.animate({
                    right: '-' + width,
                }, "slow")
            }
            if ($(this).hasClass('active')) {
                $(this).removeClass('active')
            } else {
                $(this).addClass('active')
            }
            return false;
        });
        $('#loadMoreOrders').click(loadMoreOrders());

        $(".wpb_accordion").each(function (element) {
            var $this = $(this);
            var $this_second = $(this);
            var interval = ($this_second.attr("data-interval"), !isNaN($(this).data("active-tab")) && 0 < parseInt($this_second.data("active-tab"), 10) && parseInt($this_second.data("active-tab")) - 1, 10);
            var collapsible = !1 === interval || "yes" === $this_second.data("collapsible");
            $this = $this_second.find(".wpb_accordion_wrapper").accordion({
                header: "> div > h3",
                autoHeight: !1,
                heightStyle: "content",
                active: interval,
                collapsible: collapsible,
                navigation: !0,
                change: function (element, $this) {
                    "undefined" != typeof $.fn.isotope && $this.newContent.find(".isotope").isotope("layout"), carouselBehaviour($this.newPanel)
                }
            }), !0 === $this_second.data("vcDisableKeydown") && ($this.data("uiAccordion")._keydown = function () {
            })
        });
    };
    initBtn();


}(jQuery))
$(document).ready(function () {
    let init = function () {
        initBuyBtn();
        $('#addProductPopup .count').change(calculateCost);
        $('#loadMore').click(loadMoreProducts);
        $('#addToCart').click(addProductToCart);
        $('.remove-product').click(removeProductFromCart);
    };
    let showPopup = function () {
        let idProduct = $(this).attr('data-id-product');
        let product = $('#product' + idProduct);
        $('#addProductPopup').attr('data-id-product', idProduct);
        $('#addProductPopup .category').text(product.find('.category').text());
        $('#addProductPopup .producer').text(product.find('.producer').text());
        let price = parseInt(product.find('.price').text());
        $('#addProductPopup .price').text(price + ' ₽');
        $('#addProductPopup .count').val(1);
        $('#addProductPopup .cost').text(price + ' ₽');
        let imageLink = product.find('.card-img-top').attr('src');
        $('#addProductPopup .product-image').attr('src', imageLink);
    };
    let initBuyBtn = function () {
        $('.buy-btn').click(showPopup);
    };
    let addProductToCart = function () {
        let btn = $('#addToCart');
        let idProduct = $('#addProductPopup').attr('data-id-product');
        let count = $('#addProductPopup .count').val();
        let url = '/ajax/json/product/add';
        $.ajax({
            url: url,
            method: 'POST',
            data: {
                idProduct: idProduct,
                count: count,
            },
            success: function (data) {
                $('#currentShoppingCart .total-count').text(data.totalCount);
                $('#currentShoppingCart .total-cost').text(data.totalCost);
                $('#currentShoppingCart').removeAttr('hidden');
                $('#addProductPopup').modal('hide');
            },
            error: function (xhr) {
                if (xhr.status == 400) {
                    alert(xhr.responseJSON.message);
                } else {
                    alert('Error');
                }
            }
        })
    };

    let removeProductFromCart = function () {
        let btn = $(this);
        let idProduct = btn.attr('data-id-product');
        let count = btn.attr('data-count');
        remove(idProduct, count);
    }
    // if (count==1 || btn.contains('remove-all')){
    //     confirm('Вы уверены?', remove(idProduct,count));
    // }
    let remove = function (idProduct, count) {
        let url = '/ajax/json/product/delete';
        $.ajax({
            url: url,
            method: 'POST',
            data: {
                idProduct: idProduct,
                count: count,
            },
            success: function (data) {
                if (data.totalCount == 0) {
                    window.location.href = '/products';
                } else {
                    let prevCount = parseInt($('#product' + idProduct + ' .count').text());
                    let removeCount = parseInt(count);
                    if (removeCount >= prevCount) {
                        $('#product' + idProduct).remove();
                    } else {
                        $('#product' + idProduct + ' .count').text(prevCount - removeCount);
                        if (prevCount - removeCount == 1) {
                            $('#product' + idProduct + ' a.remove-product.all').remove();
                        }
                    }
                    refreshTotalCost();
                }
            },
            error: function (xhr) {
                alert('Error');
            }
        });
    }


    let calculateCost = function () {
        let priceStr = $('#addProductPopup .cost').text();
        let price = parseInt($('#addProductPopup .price').text());
        let count = parseInt($('#addProductPopup .count').val());
        let min = parseInt($('#addProductPopup .count').attr('min'));
        let max = parseInt($('#addProductPopup .count').attr('max'));
        if (count <= max && count >= min) {
            $('#addProductPopup .cost').text(count * price + ' ₽');
        } else {
            $('#addProductPopup .count').val(1);
            $('#addProductPopup .cost').text(priceStr);
        }
    };
    let refreshTotalCost = function () {
        let totalCost = 0;
        let totalCount = 0;
        $('#shoppingCart .item').each(function (index, value) {
            let price = parseInt($(value).find('.price').text().replace(' ₽', ''));
            let count = parseInt($(value).find('.count').text());
            totalCost = totalCost + price * count;
            totalCount = totalCount + count;
        })
        $('#shoppingCart .total').text(totalCost);
        $('#currentShoppingCart .total-count').text(totalCount);
    };
    let loadMoreProducts = function () {
        let btn = $('#loadMore');
        let pageNumber = parseInt($('#productList').attr('data-page-number'));
        let url = '/ajax/html/more' + location.pathname + '?page=' + pageNumber + '&' + location.search.substring(1);
        $.ajax({
            url: url,
            success: function (html) {
                $('#productList .row').append(html);
                pageNumber++;
                let pageCount = parseInt($('#productList').attr('data-page-count'));
                $('#productList').attr('data-page-number', pageNumber);
                if (pageCount === pageNumber) {
                    btn.remove();
                }
                initBuyBtn();
            },
            error: function (data) {
                alert('Error');
            }
        });
    };
    let confirm = function (message, okFunction) {
        window.confirm(message);
        okFunction();
    }
    init();
});
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="main" class="theme-clearfix" role="document">
    <div class="breadcrumbs theme-clearfix">
        <div class="container">

            <ul class="breadcrumb">
                <li>
                    <a id="home" href="/products">Home</a>
                    <span class="go-page"></span>
                </li>

                <li >
                    <a id="orders" href="/orders">Orders</a>
                    <span class="go-page"></span>
                </li>
                <li class="active">
                    <span> #</span>
                </li>
            </ul>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div id="contents" role="main" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="post-27 page type-page status-publish hentry">
                    <div class="entry-content">
                        <div class="vc_row wpb_row vc_row-fluid">
                            <div class="wpb_column vc_column_container vc_col-sm-12">
                                <div class="vc_column-inner ">
                                    <div class="wpb_wrapper">
                                        <div class="woocommerce">
                                                <table class="shop_table cart" cellspacing="0">
                                                    <thead>
                                                    <tr>
                                                        <th class="product-thumbnail">Фото</th>
                                                        <th class="product-name">Продукт</th>
                                                        <th class="product-price">Цена</th>
                                                        <th class="product-quantity">Количество</th>
                                                        <th class="product-subtotal">Стоимость</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${order.itemList}" var="item">
                                                        <tr class="cart_item" id="${item.id}">

                                                            <td class="product-thumbnail">
                                                                <a href="">
                                                                    <img width="180" height="180" src="${item.product.imageLinkSmall}" class="attachment-shop_thumbnail size-shop_thumbnail wp-post-image" alt=""/>
                                                                </a>
                                                            </td>

                                                            <td class="product-name">
                                                                <a href="">${item.product.name}</a>
                                                            </td>

                                                            <td class="product-price">
																<span class="woocommerce-Price-amount amount">${item.product.price}<span class="woocommerce-Price-currencySymbol"> ₽</span>
																</span>
                                                            </td>

                                                            <td class="product-quantity">
                                                                <span class="quantity">${item.count}</span>
                                                            </td>

                                                            <td class="product-subtotal">
                                                                <span class="woocommerce-Price-amount amount">${item.subTotal}<span class="woocommerce-Price-currencySymbol"> ₽</span></span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>


                                            <div class="cart-collaterals">
                                                <div class="cart_total">
                                                    <div class="cart_totals ">
                                                        <h2>Сумма заказа</h2>
                                                        <table cellspacing="0" class="shop_table shop_table_responsive">
                                                            <tbody>
                                                            <tr class="order-total">
                                                                <th>Итого</th>
                                                                <td>
                                                                    <strong>
                                                                        <span class="woocommerce-Price-amount amount">${order.totalCost}</span><span class="woocommerce-Price-currencySymbol"> ₽</span>
                                                                    </strong>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END MAIN -->

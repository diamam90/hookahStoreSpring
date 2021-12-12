<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <div id="main" class="theme-clearfix" role="document">
        <div class="breadcrumbs theme-clearfix">
            <div class="container">
                <ul class="breadcrumb">
                    <li>
                        <a id="home" href="/products">Home</a>
                        <span class="go-page"></span>
                    </li>

                    <li class="active">
                        <span>Cart</span>
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
                                                <form class="shopping-cart">
                                                    <table class="shop_table cart" cellspacing="0">
                                                        <thead>
                                                        <tr>
                                                            <th class="product-remove">&nbsp;</th>
                                                            <th class="product-thumbnail">Фото</th>
                                                            <th class="product-name">Продукт</th>
                                                            <th class="product-price">Цена</th>
                                                            <th class="product-quantity">Количество</th>
                                                            <th class="product-subtotal">Стоимость</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${CURRENT_SHOPPING_CART.products}" var="entry">
                                                        <tr class="cart_item" id="${entry.key}">
                                                            <td class="product-remove">
                                                                <a href class="remove" title="Remove this item">×</a>
                                                            </td>

                                                            <td class="product-thumbnail">
                                                                <a href="">
                                                                    <img 	width="180" height="180" src="${entry.value.product.imageLinkSmall}" class="attachment-shop_thumbnail size-shop_thumbnail wp-post-image" alt=""/>
                                                                </a>
                                                            </td>

                                                            <td class="product-name">
                                                                <a href="">${entry.value.product.name}</a>
                                                            </td>

                                                            <td class="product-price">
																<span class="woocommerce-Price-amount amount">${entry.value.product.price}<span class="woocommerce-Price-currencySymbol"> ₽</span>
																</span>
                                                            </td>

                                                            <td class="product-quantity">
                                                                <div class="quantity">
                                                                    <input type="number" step="1" min="0" max="" value="${entry.value.count}" title="Qty" class="input-text qty text" size="4" pattern="[0-9]*" inputmode="numeric" />
                                                                </div>
                                                            </td>

                                                            <td class="product-subtotal">
																		<span class="woocommerce-Price-amount amount">${entry.value.subTotal}<span class="woocommerce-Price-currencySymbol"> ₽</span>
																		</span>
                                                            </td>
                                                            <tr>
                                                                <td colspan="6" class="actions">
                                                                    <input type="button" class="button" name="update_cart" value="Update Cart" data-id-product="product${entry.key}">
                                                                </td>
                                                            </tr>
                                                        </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </form>

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
																			<span class="woocommerce-Price-amount amount">${CURRENT_SHOPPING_CART.totalCost}</span><span class="woocommerce-Price-currencySymbol"> ₽</span>
                                                                        </strong>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                            <c:choose>
                                                                <c:when test="${CURRENT_ACCOUNT==null}">
                                                                    <div class="wc-proceed-to-checkout">
                                                                        <a href="/login" class="checkout-button button alt wc-forward">Войти</a>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="wc-proceed-to-checkout">
                                                                        <a href="javascript:void(0);" data-url="/order" class="post-request checkout-button button alt wc-forward">Сделать заказ</a>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
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

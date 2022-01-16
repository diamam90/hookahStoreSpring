<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hookahstore" tagdir="/WEB-INF/tags" %>

<div id="main" class="theme-clearfix" role="document">
    <div class="breadcrumbs theme-clearfix">
        <div class="container">
            <ul class="breadcrumb">
                <li>
                    <a id="home" href="/products">Home</a>
                    <span class="go-page"></span>
                </li>

                <li class="active">
                    <span>Products</span>
                </li>
            </ul>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div id="contents" class="content col-lg-12 col-md-12 col-sm-12" role="main">
                <div id="container">
                    <div id="content" role="main">
                        <div class="products-wrapper">
                            <div class="listing-title">
                                <h1>
                                    <span>Shop</span>
                                </h1>
                            </div>

                            <div class="products-nav">
                                <ul class="view-mode-wrap">
                                    <li class="view-grid sel">
                                        <a></a>
                                    </li>
                                    <li class="view-list">
                                        <a></a>
                                    </li>
                                </ul>


                                <nav class="woocommerce-pagination">
                                    <jsp:include page="page-numbers.jsp"/>
                                </nav>
                            </div>

                            <div class="clear"></div>

                            <ul class="products-loop grid clearfix">
                                <li class="clearfix divider-product"></li>
                                <c:forEach  var="p" items="${products}">
                                <li class="col-lg-3 col-md-4 col-sm-6 col-xs-12">

                                    <div class="products-entry clearfix" id="${p.id}">
                                        <div class="products-thumb">
                                                <div class="product-thumb-hover">
                                                    <img width="300" height="300"
                                                         class="attachment-shop_catalog size-shop_catalog wp-post-image"
                                                         src="${p.imageLinkSmall}"/>
                                                </div>
                                            <a type="button" data-id-product="product${p.id}" data-fancybox-type="ajax"
                                               class="fancybox fancybox.ajax sm_quickview_handler-list"
                                               title="Quick View Product">Quick View</a>
                                        </div>

                                        <div class="products-content">
                                            <div class="item-content">
                                                <h4 class="category">
                                                    <p>Категория: ${p.category}</p>
                                                </h4>
                                                <h4 class="name">${p.name}</h4>

                                                <div class="reviews-content">
                                                    <div class="star">
                                                        <span style="width: 0px"></span>
                                                    </div>
                                                </div>

                                                <div class="item-price">
                                                    <span>
														<span class="woocommerce-Price-amount amount">${p.price}<span class="woocommerce-Price-currencySymbol"> &#8381</span></span>
                                                    </span>
                                                </div>

                                                <div class="desc std">
                                                    <p>${p.description}</p>
                                                </div>
                                                <div class="item-bottom clearfix">
                                                    <div class="clear"></div>
                                                    <a class="button product_type_simple add_to_cart_button ajax_add_to_cart" id="${p.id}">Добавить в корзину
                                                    </a >
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                </li>
                                </c:forEach>
                            </ul>

                            <div class="products-nav">
                                <ul class="view-mode-wrap">
                                    <li class="view-grid sel">
                                        <a></a>
                                    </li>

                                    <li class="view-list">
                                        <a></a>
                                    </li>
                                </ul>

                                <nav class="woocommerce-pagination">
                                    <jsp:include page="page-numbers.jsp"/>
                                </nav>
                            </div>

                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

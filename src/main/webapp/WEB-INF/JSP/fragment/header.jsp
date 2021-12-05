<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HEADER -->
<div id="yt_header" class="yt-header wrap">
    <div class="header-style1">
        <div class="yt-header-top">
            <div class="container">
                <div class="row">
                    <!-- INFORMATION -->
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 sl-header-text">
                        <div class="offer-wrapper">
                            <div class="offer-header">
                                <ul id="offer-info">
                                    <li>
                                        <i class="sp-ic fa fa-phone-square">&nbsp;</i>Telephone: <a
                                            title="0123 456 7891" href="#">0123 456 7891</a>
                                    </li>

                                    <li>
                                        <i class="sp-ic fa fa-envelope">&nbsp;</i>E-mail: <a
                                            title="Contact@domain.com" href="mailto:Contact@domain.com">Contact@domain.com</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- END INFORMATION -->

                    <!-- LANGUAGE -->
                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 top-links-action">
                        <div class="block-action-header language-switcher pull-right">
                            <div class="textwidget">
                                <div id="lang_sel">
                                    <ul class="nav">
                                        <li>
                                            <a class="lang_sel_sel icl-en" href="#">
                                                <img class="iclflag" title="English" alt="en"
                                                     src="/static/images/en.png" width="18" height="12">&nbsp;ENG
                                            </a>
                                            <ul>
                                                <li class="icl-en active">
                                                    <a href="#">
                                                        <img class="iclflag" title="English" alt="en"
                                                             src="/static/images/en.png" width="18" height="12">&nbsp;ENG
                                                    </a>
                                                </li>

                                                <li class="icl-ar">
                                                    <a href="#">
                                                        <img class="iclflag" title="Arabic" alt="ar"
                                                             src="/static/images/ar.png" width="18" height="12">&nbsp;ARA
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- END LANGUAGE -->

                        <!-- CURRENCY -->
                        <div class="block-action-header block-currency pull-right">
                            <ul class="currency_w">
                                <li>
                                    <a href="#">USD</a>
                                    <ul class="currency_switcher">
                                        <li>
                                            <a href="#" class="reset default active" data-currencycode="USD">USD</a>
                                        </li>
                                        <li>
                                            <a href="#" data-currencycode="EUR">EUR</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!-- END CURRENCY -->

                        <!-- USER ACCOUNT -->
                        <div class="block-action-header top-link-account my-account-link my-account-link-other pull-right">
                            <ul id="menu-my-account" class="menu">
                                <li class="dropdown menu-my-account ya-menu-custom level1">
                                    <a href="#" class="item-link dropdown-toggle">
												<span class="have-title">
													<span class="menu-title">${currentAccount!=null? currentAccount.name:"My Account"}</span>
												</span>
                                    </a>

                                    <ul class="dropdown-menu nav-level1 one-column ">
                                        <li class="one-column menu-checkout">
                                            <a href="/static/home_style_1_checkout.html">
														<span class="have-title">
															<span class="menu-title">Checkout</span>
														</span>
                                            </a>
                                        </li>

                                        <li class="one-column menu-my-account">
                                            <a href="/static/home_style_1_my_account.html">
														<span class="have-title">
															<span class="menu-title">My account</span>
														</span>
                                            </a>
                                        </li>

                                        <li class="one-column menu-my-cart">
                                            <a href="/static/home_style_1_cart.html">
														<span class="have-title">
															<span class="menu-title">My Cart</span>
														</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                        <div class="block-action-header top-link-account login-link pull-right">
                            <div class="top-login pull-left">
                                <ul>
                                    <li>
                                        <a href="javascript:void(0);" data-toggle="modal"
                                           data-target="#login_form"><span>Login</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="widget-6 widget rev-slider-widget-2 widget_revslider">
                            <div class="widget-inner"></div>
                        </div>
                        <!-- END USER ACCOUNT -->
                    </div>

                    <!-- REGISTER_LOGIN DIALOG -->
                    <div class="modal fade" id="login_form" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog block-popup-login">
                            <a href="javascript:void(0)" title="Close" class="close close-login"
                               data-dismiss="modal">Close</a>

                            <div class="tt_popup_login">
                                <strong>Sign in Or Register</strong>
                            </div>

                            <form class="login" action="/login" method="post">
                                <div class="block-content" style="height: 238px;">
                                    <div class="col-reg registered-account">
                                        <div class="email-input">
                                            <input type="text" class="form-control input-text username"
                                                   name="login" id="login" placeholder="Username"/>
                                        </div>

                                        <div class="pass-input">
                                            <input class="form-control input-text password" type="password"
                                                   placeholder="Password" name="password" id="password"/>
                                        </div>


                                        <div class="actions">
                                            <div class="submit-login">
                                                <input type="submit" class="button btn-submit-login"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-reg login-customer">

                                        <a href="/create-account" title="Register"
                                           class="btn-reg-popup">Create an account</a>
                                    </div>

                                    <div class="clear"></div>
                                </div>
                            </form>

                            <div class="clear"></div>
                        </div>
                    </div>
                    <!-- END REGISTER_LOGIN DIALOG -->
                </div>
            </div>
        </div>

        <!-- HEADER MENU -->
        <div class="yt-header-middle">
            <div class="container">
                <div class="row">
                    <!-- LOGO -->
                    <div class="col-lg-3 col-md-2 col-sm-12 col-xs-12 logo-wrapper">
                        <a href="/products">
                            <img src="/static/images/logo-blue.png" alt="sw shoppy" width="140" height="57"/>
                        </a>
                    </div>
                    <!-- END LOGO -->

                    <div class="col-lg-9 col-md-10 col-sm-12 col-xs-12 yt-megamenu">
                        <div class="yt-header-under">
                            <span class="sr-only">Categories</span>

                            <!-- SHOPPING CART -->
                            <div class="mini-cart-header">
                                <div class="top-form top-form-minicart minicart-product-style pull-right">
                                    <div class="top-minicart pull-right">
                                        <a class="cart-contents" href="/shopping-cart" title="View your shopping cart">
                                            <c:choose>
                                                <c:when test="${CURRENT_SHOPPING_CART!=null}">
                                                    <span class="minicart-number">${CURRENT_SHOPPING_CART.totalCount}</span> item -
                                                    <span class="woocommerce-Price-amount amount">${CURRENT_SHOPPING_CART.totalCost}</span>
                                                    <span class="woocommerce-Price-currencySymbol">₽</span >
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="minicart-number">0</span> item -
                                                    <span class="woocommerce-Price-amount amount">0</span>
                                                    <span class="woocommerce-Price-currencySymbol">₽</span >
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- END SHOPPING CART -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END HEADER MENU -->
        <!-- HEADER CATEGORIES SEARCH -->
        <div class="yt-header-under-2">
            <div class="container">
                <div class="row yt-header-under-wrap">
                    <div class="yt-main-menu col-md-12">
                        <div class="header-under-2-wrapper">
                            <div class="yt-searchbox-vermenu">
                                <div class="row">
                                    <div class="col-lg-3 col-md-4 col-sm-3 col-xs-3 vertical-mega">
                                        <!-- CATEGORIES -->
                                        <div class="ver-megamenu-header">
                                            <div class="mega-left-title">
                                                <strong>Categories</strong>
                                            </div>

                                            <div class="resmenu-container">
                                                <button class="navbar-toggle" type="button" data-toggle="collapse"
                                                        data-target="#ResMenuleftmenu">
                                                    <span class="sr-only">Категории</span>
                                                    <span class="icon-bar"></span>
                                                    <span class="icon-bar"></span>
                                                    <span class="icon-bar"></span>
                                                </button>

                                                <div id="ResMenuleftmenu" class="collapse menu-responsive-wrapper">
                                                    <ul id="menu-left-menu" class="flytheme_resmenu">
                                                        <c:forEach var="entry" items="${PRODUCER_BY_CATEGORY_MAP}">
                                                            <li class="res-dropdown menu-computers-networking">
                                                            <a class="item-link dropdown-toggle" href="/categories?${entry.key.id}">
                                                                    ${entry.key.ruName}
                                                            </a>
                                                            <span class="show-dropdown"></span>
                                                            <ul class="dropdown-resmenu">
                                                                <c:forEach items="${entry.value}" var="producer">
                                                                <li class="menu-lorem-sed-fringilla">
                                                                    <a href="/categories?cat=${entry.key.id}&prod=${producer.id}">${producer.name}</a>
                                                                </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>

                                            <ul id="menu-left-menu-1" class="vertical-megamenu flytheme-menures">
                                               <c:forEach var="entry" items="${PRODUCER_BY_CATEGORY_MAP}">
                                                <li class="dropdown menu-computers-networking ya-menu-custom level1">
                                                    <a href="/categories?cat=${entry.key.id}" class="item-link dropdown-toggle">
																<span class="have-title">
																	<span class="menu-title">${entry.key.ruName}</span>
																</span>
                                                    </a>

                                                    <ul class="dropdown-menu nav-level1 one-column ">
                                                        <c:forEach var="producer" items="${entry.value}">
                                                            <li class="one-column menu-lorem-sed-fringilla">
                                                                <a href="/categories?cat=${entry.key.id}&prod=${producer.id}">
                                                                            <span class="have-title">
                                                                                <span class="menu-title">${producer.name}</span>
                                                                            </span>
                                                                </a>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </li>
                                               </c:forEach>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="search-pro col-lg-9 col-md-8 col-sm-9 col-xs-9 no-padding-l">
                                        <a class="phone-icon-search fa fa-search" href="#" title="Search"></a>

                                        <div id="sm_searchbox_pro" class="sm-searchbox-pro">
                                            <div class="sm-searchbox-content">
                                                <form id="searchform_special" action="/search">
                                                    <div class="form-search">
                                                        <div class="cat-wrapper">
                                                            <div class="selector" id="uniform-cat">
                                                                <label class="label-search">
                                                                    <select name="cat">
                                                                        <option value="all">Все категории</option>
                                                                        <c:forEach var="category" items="${CATEGORY_LIST}">
                                                                        <option value="${category.id}">${category.ruName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div class="input-search">
                                                            <input type="text" value="" name="query"
                                                                   placeholder="Найти товар"/>
                                                        </div>

                                                        <button type="submit" title="Search"
                                                                class="fa fa-search button-search-pro form-button"></button>
                                                    </div>
                                                </form>
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
        <!-- END HEADER CATEGORIES SEARCH -->

    </div>
</div>
<!-- END HEADER -->
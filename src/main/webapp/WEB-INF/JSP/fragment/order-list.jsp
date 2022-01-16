<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="main" class="theme-clearfix" role="document">
    <div class="breadcrumbs theme-clearfix">
        <div class="container">
            <ul class="breadcrumb">
                <li>
                    <a id="home" href="/products">Home</a>
                    <span class="go-page"></span>
                </li>

                <li class="active">
                    <span>Orders</span>
                </li>
            </ul>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div id="contents" role="main" class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="single post-29 page type-page status-publish hentry">
                    <div class="entry">
                        <div class="entry-content">
                            <header>
                                <h2 class="entry-title">Мой аккаунт</h2>
                            </header>
                            <div class="entry-content">
                                <div class="woocommerce">
                                    <div class="woocommerce-MyAccount-content">
                                        <table class="order">
                                            <thead>
                                            <tr>
                                                <th class="order-number"><span class="nobr"># Заказа</span></th>
                                                <th class="order-date"><span class="nobr">Дата</span></th>
                                                <th class="order-total"><span class="nobr">Итого</span></th>
                                                <th class="order-actions"><span class="nobr">&nbsp;</span></th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <c:forEach items="${orderList}" var="item">
                                            <tr class="order">
                                                <td class="order-number" data-title="Order">
                                                    <a href="/order?id=${item.id}">#${item.id}</a>
                                                </td>
                                                <td class="order-date" data-title="Date">
                                                    <time>${item.created}</time>
                                                </td>
                                                <td class="order-total" data-title="Total">
													<span class="woocommerce-Price-amount amount">
													</span>${item.totalCost}<span class="woocommerce-Price-currencySymbol"> ₽</span> за ${item.totalCount} товара
                                                </td>

                                                <td class="order-actions" data-title="&nbsp;">
                                                    <a href="/order?id=${item.id}" class="button view">View</a>
                                                </td>
                                            </tr>
                                            </c:forEach>
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
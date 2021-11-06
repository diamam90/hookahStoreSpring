<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hookahStore" tagdir="/WEB-INF/tags" %>

<div id="shoppingCart">
    <div class="alert alert-warning hidden-print" role="alert">Для заказа необходима регистрация</div>
    <hookahStore:product-table items="${CURRENT_SHOPPING_CART.items}" totalCost="${CURRENT_SHOPPING_CART.totalCost}" showActionColumn="true"></hookahStore:product-table>
</div>

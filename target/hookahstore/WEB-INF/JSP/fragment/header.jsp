<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<div class="navbar navbar-expand">
    <div class="row px-4 py-2 col-2">
        <div class="col-2" >
            <a href="/products" class="home"><i class="fa fa-home fa-2x" aria-hidden="true"></i></a>
        </div>
        <form class="col-10" action="/search">
            <input type="search" name="query" placeholder="Найти товар..." aria="label"/>
        </form>
    </div>
    <div class="px-2 py-2 col-7">
        <h3 class="text-center text-white"> <small>Интернет-магазин</small> HookahStore</h3>
    </div>
    <div class="px-2 py-2 col-3 pull-right">
        <div class="collapse navbar-collapse" id="hookahstoreNav">
            <ul id="currentShoppingCart" class="nav navbar-nav" ${(CURRENT_SHOPPING_CART==null || CURRENT_SHOPPING_CART.totalCount==0)? 'hidden': ''}>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-shopping-bag fa-2x" aria-hidden="true"></i>Корзина (<span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span>)
                    </a>
                    <ul class="dropdown-menu shopping-cart-desc">
                        <li>Общее количество: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span></li>
                        <li>Общая стоимость: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span></li>
                        <li><a class="brn btn-secondary btn-block" href="/shopping-cart">Просмотр корзины</a></li>
                    </ul>
                </li>
            </ul>
            <button type="button" class="btn btn-light text-dark me-2">Login</button>
            <button type="button" class="btn btn-primary">Sign-up</button>
        </div>
    </div>
</div>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="p" items="${products }">
    <div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 g-3">
            <div id="product${p.id}" class="card product">
                <img src="/${p.imageLinkSmall}" class="card-img-top" alt="${p.description}">
                <div class="card-body">
                    <div class="list-group">
                        <span class="list-group-item"> <span class="name">${p.name }</span></span>
                        <span class="list-group-item"> <small>Категория:</small> <span class="category">${p.category}</span></span>
                        <span class="list-group-item"> <small>Производитель:</small> <span class="producer">${p.producer}</span></span>
                    </div>
                    <p class="card-text price">${p.price }  &#8381 <a  class="btn btn-primary pull-right buy-btn" data-bs-toggle="modal" data-bs-target="#addProductPopup" data-id-product="${p.id }">Купить</a></p>
                </div>
            </div>
    </div>
</c:forEach>
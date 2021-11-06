<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="flex-shrink-0 p-3">
    <div class="card">
       <div class="card-header">
           Каталог товаров
       </div>
            <div class="list-group">
                <div class="list-group-item category">
                    <c:forEach var="entry" items="${PRODUCER_BY_CATEGORY_MAP}">
                        <div class="btn-group col-12">
                            <a class="btn col-10" name="btn1" href="/categories?cat=${entry.key.id}">${entry.key.ruName}</a>
                            <a class="btn col-2" name="btn2" data-bs-toggle="collapse" role="button" aria-expanded="false" aria-controls="collapse${entry.key.id}" href="#collapse${entry.key.id}" >+</a>
                        </div>
                        <div class="list-group producers collapse col-11 pull-right" id="collapse${entry.key.id}">
                            <c:forEach var="producer" items="${entry.value}">
                                <a href="/categories?cat=${entry.key.id}&prod=${producer.id}" class="list-group-item">${producer.name}</a>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
    </div>
</div>
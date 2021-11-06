<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hookahstore" tagdir="/WEB-INF/tags"%>

<div id="productList" data-page-count="${pageCount}" data-page-number="1">
    <div class="row">
        <jsp:include page="../fragment/product-list.jsp"/>
    </div>
    <c:if test="${pageCount > 1}">
    <div class="text-center d-print-none m-3 ">
        <a id="loadMore" class="btn btn-secondary">Загрузить еще</a>
    </div>
    </c:if>
</div>
<hookahstore:add-product-popup/>
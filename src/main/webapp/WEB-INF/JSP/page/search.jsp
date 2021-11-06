<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="alert alert-primary my-3" role="contentinfo">
    Найдено товаров: ${totalCount }
</div>

<jsp:include page="products.jsp"/>
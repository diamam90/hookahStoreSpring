<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hookahstore" tagdir="/WEB-INF/tags"%>


<div id="productList" data-page-count="${pageCount}" data-page-number="${page}">
    <jsp:include page="../fragment/product-list.jsp"/>
</div>
<hookahstore:add-product-popup/>